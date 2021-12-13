package dev.xymox.scalaschool

import zio._
import scala.io.StdIn

object Echo extends App {
  val readLine: Task[String] = ZIO.effect(StdIn.readLine())

  def printLine(line: String): Task[Unit] = ZIO.effect(println(line))

  val echo: ZIO[Any, Throwable, Unit] = readLine.flatMap(line => printLine(line))

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = echo.exitCode
}
