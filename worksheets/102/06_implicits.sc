// implicit parameters
implicit val name = "Elliot"
val age = 24

def describe(age: Int)(implicit name: String) = s"$name is $age"
describe(age)

// implicit conversions
case class ApiPerson(firstName: String, lastName: String)

case class Person(age: Option[Int], firstName: String, lastName: String)
object Person {
  implicit def convertFromApi(apiPerson: ApiPerson) = Person(Option.empty, apiPerson.firstName, apiPerson.lastName)
}

def describe(person: Person) = s"${person.firstName} is ${person.age.fold("Unknown")(a => a.toString)} years old."

val elliotApi = ApiPerson("Elliot", "Alderson")
val angelaPerson = Person(Option(25), "Angela", "Moss")

describe(elliotApi)
describe(angelaPerson)

// implicit classes
object Helpers {
  implicit class IntWithTimes(x: Int) {
    def times[A](f: => A): Unit = {
      def loop(current: Int): Unit =
        if (current > 0) {
          f
          loop(current - 1)
        }
      loop(x)
    }
  }
}

import Helpers._
5.times(println("HI"))