import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}

// Futures cannot run in the worksheet unfortunately w/o an Await :(

val futureAge = Future(32)

futureAge.onComplete {
  case Success(age) => println(age)
  case Failure(ex) => ex.printStackTrace()
}

val futureName = Future("Elliot")

val mapResults = futureName.map("Name: " + _)

Await.result(mapResults, 1.second)

val changeName: String => String = (name) => s"Name: $name"

val futureAnotherName = Future("Angela")
val nameResults = futureAnotherName.map(changeName).recover {
  case ex: Exception => s"Name Unknown due to error: ${ex.getMessage}"
}

Await.result(nameResults, 1.second)

val future1 = Future(1)
val future2 = Future(2)

val results: Future[Int] = for {
  one <- future1
  two <- future2
  if (two == 2)
} yield one + two

Await.result(results, 1.second)