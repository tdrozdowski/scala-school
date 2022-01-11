package dev.xymox.scalaschool.tax

import dev.xymox.scalaschool.repositories.tax.{TaxError, TaxRepository}
import zio.test.Assertion._
import zio.test._
import zio.test.environment.TestEnvironment

object TaxRepositorySpec extends DefaultRunnableSpec {

  override def spec: ZSpec[TestEnvironment, Any] = suite("TaxRepository")(
    testM("finds a tax rate for a zip") {
      for {
        results <- TaxRepository.findForZip("85260").provideLayer(TaxRepository.provided)
      } yield assertTrue(results == 0.10f)
    },
    testM("returns error if rate not found for zip") {
      for {
        error <- TaxRepository.findForZip("12345").flip.provideLayer(TaxRepository.provided)
      } yield assert(error)(isSubtype[TaxError](anything))
    }
  )
}
