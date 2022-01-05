import dev.xymox.scalaschool.service.exampleclock._
import zio._
import zio.console._
import zio.magic._

val currentTime = for {
  now <- ExampleClock.getCurrentTime
  _ <- putStrLn(s"Received current time is: $now")
} yield ()

val program = currentTime.inject(Console.live, ExampleClock.provided)

Runtime.default.unsafeRun(program)