import java.net.URI
import scala.util.{Failure, Success, Try}

val maybeInt: Option[Int] = Option(6)
val maybeAnotherInt: Option[Int] = Option.empty

val myInt = maybeInt.getOrElse(0)
val myOtherInt = maybeAnotherInt.getOrElse(0)

val nullInt = null
val maybeNull = Option(nullInt)

val maybeName = Option("Elliot Alderson")

maybeName.isDefined
maybeName.isEmpty

// BAD
if (maybeName.isDefined)
  println(s"Name: ${maybeName.get}")
else
  println("No person!")

maybeName.getOrElse("No name!")

val maybeUpdatedName = maybeName.map(name => Option(s"Name: $name"))

maybeUpdatedName.flatten.getOrElse("No Name!")

val maybeAnotherName = Option("Angela Moss")

val maybeNames = for {
  elliot <- maybeName
  angela <- maybeAnotherName
} yield Seq(elliot, angela)

// Try
val goodResults: Try[URI] = Try(URI.create("http://www.something.com"))
val badResults: Try[URI] = Try(URI.create("blech//\\foo"))

def uriPrinter(uri: Try[URI]): Unit = uri match {
  case Success(uri) => println(s"URI is: $uri")
  case Failure(ex) => println(s"Whoops!  Something went wrong: ${ex.getMessage}")
}

uriPrinter(goodResults)
uriPrinter(badResults)

goodResults.toOption

goodResults.getOrElse(URI.create("http://example.com"))

// Either
val results: Either[Int, String] = Right("Right Value")
val moreResults: Either[Int, String] = Left(10)

def resultsPrinter(results: Either[Int, String]) = results match {
  case Left(number) => println(s"We got a Int: $number")
  case Right(message) => println(s"We got a message: $message")
}

resultsPrinter(results)
resultsPrinter(moreResults)

results.toOption
moreResults.getOrElse("Another Right Value")