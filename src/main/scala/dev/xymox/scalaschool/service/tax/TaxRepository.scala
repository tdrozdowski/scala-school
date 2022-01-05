package dev.xymox.scalaschool.service.tax

import zio._

trait TaxRepository {
  def findForZip(zip: String): Task[Float]
}

object TaxRepository {
  def live: TaskLayer[Has[TaxRepository]] = (TaxRepositoryLive).toLayer
}

case class TaxRepositoryLive() extends TaxRepository {
  override def findForZip(zip: String): Task[Float] = ???
}
