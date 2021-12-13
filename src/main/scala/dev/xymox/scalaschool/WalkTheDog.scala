package dev.xymox.scalaschool

import zio._
import zio.clock.Clock
import zio.duration._

object WalkTheDog extends App {
  val walkTheDog: Task[Unit] = ZIO.effect(println("Walk the dog!"))

  val walkTheDogLater: ZIO[Clock, Throwable, Unit] = walkTheDog.delay(1.minute)

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = walkTheDogLater.exitCode
}
