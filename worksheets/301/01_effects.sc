import java.util.concurrent.{Executors, ScheduledExecutorService}
import java.util.concurrent.TimeUnit._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Success

val walkTheDogUnsafe: Unit = println("Walking the dog!")

val scheduler: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
scheduler.schedule(
  new Runnable { def run: Unit = walkTheDogUnsafe }, 1,
  HOURS
)
scheduler.shutdown()

// ZIO to the rescue
import zio._
import zio.duration._

val walkTheDog = ZIO.effect(println("Walking the dog!"))

val walkTheDogLater = walkTheDog.delay(1.hour)

// Echo in ZIO
import scala.io.StdIn

val readLine = ZIO.effect(StdIn.readLine())
def printLine(line: String) = ZIO.effect(println(line))
val echo = readLine.flatMap(line => printLine(line))

// zipWith
val firstName: Task[String] =
  ZIO.effect(StdIn.readLine("What is your first name?"))

val lastName: Task[String] =
  ZIO.effect(StdIn.readLine("What is your last name"))
val fullName: Task[String] =
  firstName.zipWith(lastName)((first, last) => s"$first $last")

// zipWith operator: *>
val helloWorld =
  ZIO.effect(print("Hello, ")) *> ZIO.effect(print("World!\n"))

// collectAll
import zio.console._

val prints = List(
  putStrLn("The"),
  putStrLn("quick"),
  putStrLn("brown"),
  putStrLn("fox")
)
val printWords = ZIO.collectAll(prints)

// for pure values:
val pureSuccess = ZIO.succeed(2 + 2)
val pureFalure = ZIO.fail(new Exception("BOOM!"))

// other constructors
val ec = scala.concurrent.ExecutionContext.Implicits.global

val fromOption: IO[Option[String], String] = ZIO.fromOption(Option("some value"))
val fromEither: IO[String, String] = ZIO.fromEither(Right("a value"))
val fromTry: Task[String] = ZIO.fromTry(Success("a value"))
val fromFuture: Task[Int] = ZIO.fromFuture(implicit ec => Future.successful(6))

// recursion with ZIO

val readInt: RIO[Has[Console], Int] = (for {
  line <- getStrLn
  int <- ZIO.effect(line.toInt)
} yield int).provideLayer(Console.live)

lazy val readIntOrRetry: URIO[Has[Console], Int] =
  readInt
    .orElse(putStrLn("Please enter a valid integer")
    .zipRight(readIntOrRetry))


