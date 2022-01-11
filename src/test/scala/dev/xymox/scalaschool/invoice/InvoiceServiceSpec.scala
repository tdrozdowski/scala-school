package dev.xymox.scalaschool.invoice

import dev.xymox.scalaschool.service.invoice.InvoiceService
import zio.test.Assertion.anything
import zio.{Has, TaskLayer, UIO, ZIO, ZLayer}
import zio.test.environment.TestEnvironment
import zio.test._
import zio.test.Assertion._
import zio.test.mock.Expectation._
import zio.test.mock.{mockable, Expectation}
import zio.magic._

object InvoiceServiceSpec extends DefaultRunnableSpec {

  def fail: UIO[Assert] = ZIO.succeed(assertTrue(false))

  override def spec: ZSpec[TestEnvironment, Any] = suite("InvoiceService")(
    suite("applyTax")(
      testM("applies the correct tax for a given zip") {
        for {
          results <- InvoiceService.applyTax(100, "85260-8442").provideLayer(InvoiceFixtures.successApplyTaxLayer)
        } yield assertTrue(results == 110)
      },
      testM("applies the correct tax for a set of given values") {
        checkM(Gen.anyFloat, Gen.alphaNumericString, Gen.anyFloat) { (subTotal, zipCode, taxRate) =>
          for {
            results      <- InvoiceService.applyTax(subTotal, zipCode).provideLayer(InvoiceFixtures.successApplyTaxLayer(taxRate))
            expectedTotal = (subTotal * taxRate) + subTotal
          } yield assertTrue((expectedTotal == results))
        }
      },
      testM("surfaces an error when tax rate is not found for a given zip") {
        for {
          error <- InvoiceService.applyTax(100, "85260-8442").flip.provideLayer(InvoiceFixtures.failureApplyTaxLayer)
        } yield assert(error)(isSubtype[TaxError](anything))
      }
    )
  )
}

object InvoiceFixtures {

  @mockable[TaxRepository]
  object MockTaxRepository

  def findForZipSuccess: Expectation[Has[TaxRepository]] =
    MockTaxRepository.FindForZip(anything, value(0.10f))

  def findForZipFailure: Expectation[Has[TaxRepository]] =
    MockTaxRepository.FindForZip(anything, failure(TaxRateNotFound))

  def findForZipSuccess(taxRate: Float): Expectation[Has[TaxRepository]] =
    MockTaxRepository.FindForZip(anything, value(taxRate))

  def successApplyTaxLayer: TaskLayer[Has[InvoiceService]] = ZLayer.wire[Has[InvoiceService]](findForZipSuccess, InvoiceService.live)

  def successApplyTaxLayer(taxRate: Float): TaskLayer[Has[InvoiceService]] = ZLayer.wire[Has[InvoiceService]](findForZipSuccess(taxRate), InvoiceService.live)

  def failureApplyTaxLayer: TaskLayer[Has[InvoiceService]] = ZLayer.wire[Has[InvoiceService]](findForZipFailure, InvoiceService.live)
}
