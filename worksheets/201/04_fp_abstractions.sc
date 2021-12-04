// semigroups
trait Semigroup[T] {
  def combine(a: T, b: T): T
}

object Semigroup {
  def apply[A](implicit instance: Semigroup[A]): Semigroup[A] = instance
}

object SemigroupInstances {
  implicit val forInt: Semigroup[Int] = new Semigroup[Int] {
    override def combine(a: Int, b: Int) = a + b
  }

  implicit val forString: Semigroup[String] = new Semigroup[String] {
    override def combine(a: String, b: String) = a + b
  }
}

import SemigroupInstances._

val intSemigroup = Semigroup[Int]
val stringSemigroup = Semigroup[String]

val meaningOfLife = intSemigroup.combine(40,2)
val language = stringSemigroup.combine("Sca", "la")

// monoid
trait Monoid[T] extends Semigroup[T] {
  def empty: T
}

object IntInstances {
  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    override def combine(a: Int, b: Int) = a + b
    override def empty: Int = 0
  }
}

object StringInstances {
  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    override def combine(a: String, b: String) = a + b
    override def empty: String = ""
  }
}

import StringInstances._

def stringConcat(strings: Seq[String])(implicit m: Monoid[String]) = strings.foldLeft(m.empty)(m.combine)

val someStrings: Seq[String] = Seq("This ", "is ", "simple ", "yet ", "powerful!")

stringConcat(someStrings)