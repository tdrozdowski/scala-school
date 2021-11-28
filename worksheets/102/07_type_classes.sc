sealed trait Animal
final case class Dog(name: String) extends Animal
final case class Cat(name: String) extends Animal

trait BehavesLikeHuman[A] {
  def speak(a: A): Unit
}

object BehavesLikeHumanInstances {
  implicit val forDog = new BehavesLikeHuman[Dog] {
    def speak(dog: Dog): Unit = println(s"I'm a Dog, my name is ${dog.name}")
  }
}

object BehavesLikeHumanSyntax {
  implicit class BehavesLikeHumanOps[A](value: A) {
    def speak(implicit a: BehavesLikeHuman[A]): Unit = a.speak(value)
  }
}

import BehavesLikeHumanInstances._
import BehavesLikeHumanSyntax._

val dasher = Dog("Dasher")
dasher.speak

