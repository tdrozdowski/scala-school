package dev.xymox.scalaschool.repositories.tax

import zio._

sealed trait TaxError
object TaxRateNotFound extends TaxError

trait TaxRepository {
  def findForZip(zip: String): IO[TaxError, Float]
}

object TaxRepository {
  def live: TaskLayer[Has[TaxRepository]] = (TaxRepositoryLive).toLayer
}

case class TaxRepositoryLive() extends TaxRepository {
  override def findForZip(zip: String): IO[TaxError, Float] = ???
}
