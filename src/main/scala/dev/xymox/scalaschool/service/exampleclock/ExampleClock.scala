package dev.xymox.scalaschool.service.exampleclock

import zio._
import java.time.Instant


trait ExampleClock {
  def getCurrentTime: Task[Instant]
}

object ExampleClock {
  def getCurrentTime: RIO[Has[ExampleClock], Instant] =
    ZIO.serviceWith[ExampleClock](_.getCurrentTime)

  def provided: TaskLayer[Has[ExampleClock]] = ExampleClockLive.provided
}