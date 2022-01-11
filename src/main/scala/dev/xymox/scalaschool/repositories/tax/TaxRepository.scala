package dev.xymox.scalaschool.repositories.tax

import io.getquill.{PostgresZioJdbcContext, SnakeCase}
import io.getquill.context.ZioJdbc.DataSourceLayer
import io.getquill.context.qzio.ImplicitSyntax.{Implicit, ImplicitSyntaxOps}
import zio._

import javax.sql.DataSource
import zio.magic._

sealed trait TaxError
object TaxRateNotFound extends TaxError
object UnexpectedError extends TaxError

trait TaxRepository {
  def findForZip(zip: String): IO[TaxError, Float]
}

object TaxRepository {

  def findForZip(zip: String): ZIO[Has[TaxRepository], TaxError, Float] =
    ZIO.serviceWith[TaxRepository](_.findForZip(zip))

  def live: URLayer[Has[DataSource], Has[TaxRepository]] = (TaxRepositoryLive).toLayer

  def provided: TaskLayer[Has[TaxRepository]] = ZLayer.wire[Has[TaxRepository]](DataSourceLayer.fromPrefix("scalaSchoolDb"), live)
}

case class TaxRepositoryLive(dataSource: DataSource) extends TaxRepository {
  val ctx = new PostgresZioJdbcContext(SnakeCase)
  import ctx._

  implicit val env = Implicit(Has(dataSource))

  //noinspection TypeAnnotation
  implicit val taxLookupSchemaMeta = schemaMeta[TaxLookup]("tax_tables")

  override def findForZip(zip: String): IO[TaxError, Float] =
    ctx
      .run(Queries.forZip(zip))
      .implicitly
      .map(_.headOption)
      .someOrFail(TaxRateNotFound)
      .mapBoth(_ => UnexpectedError, _.localTaxRate)

  object Queries {

    def forZip(zip: String) = quote {
      query[TaxLookup].filter(_.zip == lift(zip))
    }
  }
}

case class TaxLookup(id: Long, zip: String, localTaxRate: Float)
