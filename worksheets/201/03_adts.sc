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
sealed trait Veggie
case object Cheese extends Topping
case object Pepperoni extends Topping
case object Sausage extends Topping
case object Ham extends Topping
case object Mushrooms extends Topping with Veggie
case object Onions extends Topping with Veggie
case object GreenPeppers extends Topping with Veggie
case object Pineapple extends Topping

case class Pizza(crustSize: CrustSize, crustType: CrustType, toppings: Seq[Topping])

val myOrder = Pizza(Large, Thin, Seq(Cheese, Pepperoni, Sausage))

val anotherOrder = Pizza(Medium, Pan, Seq(GreenPeppers, Mushrooms, Onions))

val abomination = Pizza(Large, Traditional, Seq(Ham, Pineapple) )

println(myOrder)

def isVeggie(topping: Topping): Boolean = topping match {
  case _: Veggie => true
  case _ => false
}

def isHawaiian(topping: Topping): Boolean = topping match {
  case Ham => true
  case Pineapple => true
  case _ => false
}

def checkForSpecialty(pizza: Pizza, specFunc: Topping => Boolean): Boolean = pizza.toppings.map(specFunc).reduce(_ && _)

def isVegitarianPizza(pizza: Pizza) = checkForSpecialty(pizza, isVeggie)

def isHawaiianPizza(pizza: Pizza) = checkForSpecialty(pizza, isHawaiian)

isVegitarianPizza(myOrder)
isVegitarianPizza(anotherOrder)
isHawaiianPizza(myOrder)
isVegitarianPizza(abomination)
isHawaiianPizza(abomination)

