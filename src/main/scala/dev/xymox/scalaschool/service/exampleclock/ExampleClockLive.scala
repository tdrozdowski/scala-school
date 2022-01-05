package dev.xymox.scalaschool.service.exampleclock

import zio.{Has, Task, TaskLayer, URLayer, ZLayer}
import zio.console.Console
import zio.magic._

import java.time.Instant

case class ExampleClockLive(console: Console.Service) extends ExampleClock {
  override def getCurrentTime: Task[Instant] =
    for {
      now <- Task(Instant.now)
      _ <- console.putStrLn(s"Generated current time: $now")
    } yield now
}

object ExampleClockLive {
  val live: URLayer[Has[Console.Service], Has[ExampleClock]] = (ExampleClockLive(_)).toLayer
  val provided: TaskLayer[Has[ExampleClock]] = ZLayer.wire[Has[ExampleClock]](Console.live, live)
}