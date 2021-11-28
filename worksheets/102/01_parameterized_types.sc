abstract class Animal {
  def name: String
}

case class Dog(name: String) extends Animal
case class Cat(name: String) extends Animal

// invariance - parameterized types are invariant by default
case class Container[A](value: A) {
  def setValue[A](value: A): Container[A] = Container(value)
}

val catContainer: Container[Cat] = new Container(Cat("Felix"))
val cat: Cat = catContainer.value
// You can't do...
//val animalContainer: Container[Animal] = catContainer
//animalContainer.setValue(Dog("Spot"))
// but you can do...
val animalContainer: Container[Animal] = Container(Dog("Spot"))
animalContainer.setValue(Cat("Felix"))

// covariance
case class Container[+A](value: A) {
  def setValue[B >: A](value: B): Container[B] = Container(value)
}

val catContainer: Container[Cat] = Container(Cat("Felix"))
val animalContainer: Container[Animal] = catContainer
animalContainer.setValue(Dog("Spot"))


// covariance - a List[Dog] is covariant to List[Animal]
def printAnimalNames(animals: List[Animal]): Unit = {
  animals.foreach { animal =>
    println(animal.name)
  }
}

val cats: List[Cat] = List(Cat("Whiskers"), Cat("Tom"))
val dogs: List[Dog] = List(Dog("Fido"), Dog("Rex"))

printAnimalNames(cats)
printAnimalNames(dogs)

// contravariance
trait PetHotel[-A <: Animal] {
  def checkin(pet: A): Boolean
}

val animalPetHotel  = new PetHotel[Animal] {
  def checkin(pet: Animal) = true
}

val dasher = Dog("Dasher")
val boomer = Cat("Boomer")
val dogPetHotel: PetHotel[Dog] = animalPetHotel
dogPetHotel.checkin(dasher)
animalPetHotel.checkin(boomer)

