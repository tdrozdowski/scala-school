package dev.xymox.scalaschool.service.invoice

import dev.xymox.scalaschool.service.tax.{TaxError, TaxRepository}
import zio._

trait InvoiceService {
  def applyTax(subTotal: Float, zipCode: String): IO[TaxError, Float]
}

object InvoiceService {

  def applyTax(subTotal: Float, zipCode: String): ZIO[Has[InvoiceService], TaxError, Float] =
    ZIO.serviceWith[InvoiceService](_.applyTax(subTotal, zipCode))

  def live: URLayer[Has[TaxRepository], Has[InvoiceService]] = (InvoiceServiceLive(_)).toLayer
}

case class InvoiceServiceLive(taxRepository: TaxRepository) extends InvoiceService {

  override def applyTax(subTotal: Float, zipCode: String): IO[TaxError, Float] =
    for {
      taxRate     <- taxRepository.findForZip(zipCode)
      amountOfTax <- ZIO.succeed(taxRate * subTotal)
      newTotal    <- ZIO.succeed(subTotal + amountOfTax)
    } yield newTotal
}
