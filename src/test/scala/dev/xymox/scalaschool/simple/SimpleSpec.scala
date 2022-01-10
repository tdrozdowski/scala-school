package dev.xymox.scalaschool.simple

import zio.ZIO
import zio.test.environment.TestEnvironment
import zio.test.{assertTrue, DefaultRunnableSpec, ZSpec}

object SimpleSpec extends DefaultRunnableSpec {

  override def spec: ZSpec[TestEnvironment, Any] = suite("simple test suite")(
    testM("a pretty simple test") {
      for {
        results <- ZIO.succeed(1 + 1)
      } yield assertTrue(results == 2) && assertTrue(true) || assertTrue(false)
    }
  )
}
