case class Person(age: Int, firstName: String, lastName: String)
object Person {
  def fullName(p: Person): String = s"${p.firstName} ${p.lastName}"
}

val angela = Person(25, "Angela", "Moss")

Person.fullName(angela)

//angela.age = 46

val updatedAngela = angela.copy(age = 46)

angela == updatedAngela

val angela2 = Person(25, "Angela", "Moss")

angela == angela2