package dev.xymox.scalaschool.service.invoice

import dev.xymox.scalaschool.service.tax.TaxRepository
import zio._

trait InvoiceService {
  def applyTax(subTotal: Float, zipCode: String): Task[Float]
}

object InvoiceService {

  def applyTax(subTotal: Float, zipCode: String): RIO[Has[InvoiceService], Float] =
    ZIO.serviceWith[InvoiceService](_.applyTax(subTotal, zipCode))

  def live: URLayer[Has[TaxRepository], Has[InvoiceService]] = (InvoiceServiceLive(_)).toLayer
}

case class InvoiceServiceLive(taxRepository: TaxRepository) extends InvoiceService {

  override def applyTax(subTotal: Float, zipCode: String): Task[Float] =
    for {
      taxRate     <- taxRepository.findForZip(zipCode)
      amountOfTax <- ZIO.succeed(taxRate * subTotal)
      newTotal    <- ZIO.succeed(subTotal + amountOfTax)
    } yield newTotal
}
