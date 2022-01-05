import java.time._
import zio._

// service module v1

object ExampleClock {
  // alternatively - this can live in a package object
  type ExampleClock = Has[ExampleClock.Service]
  trait Service {
    def getCurrentTime: Task[Instant]
  }
  // accessor method; you can generate these with @accessible annotated on the object
  def getCurrentTime: RIO[ExampleClock, Instant] = RIO.accessM(_.get.getCurrentTime)

  // our 'live' instance of the service
  def live: ULayer[ExampleClock] = ZLayer.succeed {
    new Service {
      override def getCurrentTime = Task(Instant.now)
    }
  }
}

zio.clock.Clock

import zio.console._

val currentTime = for {
  now <- ExampleClock.getCurrentTime
  _ <- putStrLn(s"Current time is: $now")
} yield ()

val program = currentTime.provideLayer(Console.live ++ ExampleClock.live)

Runtime.default.unsafeRun(program)