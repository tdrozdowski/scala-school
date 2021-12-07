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

import scala.util.Try

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

// functors
val numbers = Seq(1,2,3,4)
val doubled = numbers.map(_ * 2)

val maybeTwo = Option(2)
val maybeResults = maybeTwo.map(_ * 2)
val triedResults = Try(42).map(_ + 2)

def doubleList(list: List[Int]): List[Int] = list.map(_ * 2)
def doubleOption(option: Option[Int]): Option[Int] = option.map(_ * 2)
def doubleTry(attempt: Try[Int]): Try[Int] = attempt.map(_ * 2)

trait Functor[F[_]] {
  def map[A,B](container: F[A])(func: A => B): F[B]
}

object FunctorInstances {
  implicit val listFunctor: Functor[Seq] = new Functor[Seq] {
    override def map[A, B](container: Seq[A])(func: A => B): Seq[B] = container.map(func)
  }
//  implicit val optionFunctor: Functor[Option] = new Functor[Option] {
//    override def map[A, B](container: Option[A])(func: A => B): Option[B] = container.map(func)
//  }
}

import FunctorInstances._

def double[F[_]](container: F[Int])(implicit functor: Functor[F]): F[Int] = functor.map(container)(_ * 2)

double(numbers)
//double(maybeTwo)

sealed trait Tree[+T] {
  def map[B](f: T => B): Tree[B]
}

object Tree {
  case class Leaf[+T](value: T) extends Tree[T] {
    override def map[B](f: T => B): Tree[B] = Leaf(f(value))
  }
  case class Branch[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T] {
    override def map[B](f: T => B): Branch[B] = Branch(f(value), left.map(f), right.map(f))
  }
  def leaf[T](value: T): Tree[T] = Leaf(value)
  def branch[T](value: T, left: Tree[T], right: Tree[T]): Tree[T] = Branch(value, left, right)
}

object TreeInstances {
  import Tree._
  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](container: Tree[A])(func: A => B): Tree[B] = container match {
      case Leaf(value) => Leaf(func(value))
      case Branch(value, left, right) => Branch(func(value), left.map(func), right.map(func))
    }
  }
}

val tree =
  Tree.branch(1,
    Tree.branch(2,
      Tree.leaf(3),
      Tree.leaf(4)),
    Tree.leaf(5)
  )

import TreeInstances._

double(tree)

// Monads

trait Monad[F[_]] extends Functor[F] with Monoid[F]

val maybeFour = Option(4)
val maybeMaybeResults: Option[Option[Int]] = maybeTwo.map(x => maybeFour.map(_ * x))

val maybeResults = maybeMaybeResults.flatten

val maybeResults = maybeTwo.flatMap(x => maybeFour.map(_ * x))

val maybeOne: Option[Int] = Option(1)
val maybeThree: Option[Int] = Option(3)

maybeOne.flatMap(x => maybeTwo.flatMap(y => maybeThree.map(_ + x + y)))

val results = for {
  x <- maybeOne
  y <- maybeTwo
  z <- maybeThree
} yield x + y + z

val results = for {
  x <- maybeOne
  y <- maybeTwo
  z <- maybeThree
  if (z > 3)
} yield x + y + z