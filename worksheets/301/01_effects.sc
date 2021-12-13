import java.util.concurrent.{ Executors, ScheduledExecutorService }
import java.util.concurrent.TimeUnit._

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