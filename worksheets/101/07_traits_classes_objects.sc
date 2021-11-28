trait FullName {
  def fullName: String
}

class Person(age: Int, firstName: String, lastName: String) extends FullName {
  def fullName: String = s"$firstName $lastName"
}

object Person {
  def apply(age: Int, firstName: String, lastName: String) = new Person(age, firstName, lastName)

  def printFullName(fullNamed: FullName): Unit = println(s"${fullNamed.fullName}")
}

val elliot = new Person(24, "Elliot", "Alderson")

println(s"${elliot.fullName}")

val angela = Person(25, "Angela", "Moss")

Person.printFullName(angela)

val angela2 = Person(25, "Angela", "Moss")

angela == angela2

//elliot.age = 43

val ingredient = ("Sugar", 25)
println(ingredient._1) // Sugar
println(ingredient._2) // 25