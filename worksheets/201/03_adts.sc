case class Person(age: Int, name: String)

sealed trait PrimaryColor
case object Red extends PrimaryColor
case object Blue extends PrimaryColor
case object Yellow extends PrimaryColor

case class DoubleBoolean(b1: Boolean, b2: Boolean)

DoubleBoolean(true, true)
DoubleBoolean(true, false)
DoubleBoolean(false, true)
DoubleBoolean(false, false)

case class Pair(a: Int, b: Int)

case class Person(age: Int, name: String, mother: Person, father: Person)

sealed trait CrustSize
case object Small extends CrustSize
case object Medium extends CrustSize
case object Large extends CrustSize

sealed trait CrustType
case object Thin extends CrustType
case object Traditional extends CrustType
case object Pan extends CrustType
case object GlutenFree extends CrustType

sealed trait Topping
case object Cheese extends Topping
case object Pepperoni extends Topping
case object Sausage extends Topping
case object Mushrooms extends Topping

case class Pizza(crustSize: CrustSize, crustType: CrustType, toppings: Seq[Topping])

val myOrder = Pizza(Large, Thin, Seq(Cheese, Pepperoni, Sausage))

println(myOrder)

def isVegetarian(topping: Topping): Boolean = topping match {
  case Mushrooms => true
  case _ => false
}

myOrder.toppings.map(isVegetarian).reduce(_ && _)