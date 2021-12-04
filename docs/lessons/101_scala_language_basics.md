## Overview
To learn Scala - first we need to go over the language basics.  We will go over how to use the language and some key features in this session using live coding samples via IntelliJ's Scala Worksheet.  If you aren't using IntelliJ you can also use the Scala REPL - this assumes you have Scala installed locally and know how to startup the REPL itself.

Remember - Scala is a hybrid language - its both OO and FP.  This provides a very powerful combination in terms of modeling business problems precisely and with minimal boilerplate.  As such - everything in Scala is an object.  There are not native types like in other languages and as such you can avoid common 'boxing' problems when moving between native types and their object counterpart.  Everything is also a expression in Scala.  Every expression results in a value - which is an object.  Even if, while and for statements are all expressions - and all provide a value.  This may seem odd at first if you are coming here from a language such as Java or even C# where there is a distinction between statements and expressions.  But don't worry, its easy to get used to.  And very helpful!

With a language full of values - type safety is enforced to make sure things are consistent.  Scala has a very rich type system and is very strict in maintaining that a value sticks to its type.  In fact your code won't compile if you try to define a value as a `String` but later try to wedge in an `Int` value w/o first converting it.  This helps reduce an entire class of bugs that you won't have to debug at runtime - saving you a lot of effort.  The type system is much more advanced compared to other languages -  Java and C#.  It is often the source of most difficulty when learning Scala.  So be patient and take time to learn the basics of the type system as we get started.  Once you get used to it - and realize how much time it will save later on you will learn to love it and wonder how you ever coded without it.

A noted difference between Scala and other languages (notably Java and C#) is that the type annotations are specified on the right of the variable.

FP Bingo warning!  FP carries a lot of terms that are confusing/scary and can lead to the reading of many white papers and examples in Haskell or other FP languages that are not easy to sort out.  We will attempt to use these terms lightly - demonstrate the concept and give you the opportunity to dig deeper if you so choose.  When you're getting started, often times its not important that you know the details of the terms - just how to apply them.  And the good news is that applying them is generally easy in Scala.

## IntelliJ Project Setup
A project has been setup in GitHub that contains a basic project setup for IntelliJ.  It contains the various worksheets that we're going to be going through.  Please feel free to follow along.  The great thing about worksheets are that they quickly compile any Scala code you want and give you instant results!  Its a great learning tool when you're new to Scala.

The project can be found here: https://github.com/tdrozdowski/scala-school

## Language Structure - Scala vs Java, C#, etc...
In general, the c-style programming structure is used.  

* You can scope code with `{}` (**NOTE** In Scala 3 this changes!  You can drop the `{}` and adopt a more 'Python-esque' indentation style)
* Comments are: 
  * `//` - single line
  * `/* ... */` - multiline
  * `/** ... */` - Scaladoc
* Line separators are not necessary (no `;`)
* In Scala, unlike Java and C# - you can even choose not to use the `()` when you invoke functions if you so choose.  This can lead to a more naturally readable flavor of code.  This is ok in small doses, but in general, you shouldn't do this with all your code.  (**NOTE** In Scala 3 the rules on this change - so be careful!)
* Since everything is an expression that results in a value, no `return` statement is needed
* Since every value is an object - `Unit` is the equivalent of `void` in other languages
* In Scala, `*` is not the wildcard - use `_` when you want to match for a wildcard: i.e. `import zio._`
* Like in Java, you can namespace code using packages: i.e. `package dev.xymox.scala-school.examples`
* Like in Java and C# you can use `null` - but it highly discouraged.  Scala contains a different way to handle null values - and this helps eliminate another entire classification of bugs.
* Like Java and C# you have Exceptions.  Unlike Java, these exceptions are always RuntimeExceptions.  Scala does not contain checked exceptions.
* Like Java and C# you can restrict access to methods/properties on your classes.  By default, everything is `public` so that keyword is not used.  You can also use `protected` and `private` to further restrict access as you would expect.  One thing that's different from Java is that you can restrict access to a package level as well.  You can do this by `private[mypackage]` - then this method or property will be private to everything except for anything in the package `mypackage`.

## Session 1 - Values vs Variables
When you are using Scala, your application will likely have state.  You can store this state as either a 'value' or a 'variable'.  What's the difference between them?  A concept called immutability.

### Variables

A variable is defined using the keyword `var`.  Variables are mutable (you can change their value after you set them).  For example:

```scala
var name: String = "Elliot"
```

If you don't provide the type Scala will infer it.

**NOTE** Its considered best practice to always provide a type annotation for your values in most cases.  If you are working with code that utlizes some meta-programming via macros, the types can get in the way and cause issues.  But this is a rare situation.

Sets the variable `name` to the value "Elliot".  Since its a var we can change that value after we set it...

```scala
name = "Elliot Alderson"
```

Now `name` contains the full name.

### Values
A value is defined using the keyword `val`.  Values are immutable.  Once you set the value it is unchangeable.  In fact, the code won't even compile!

```scala
val age = 42
age = 21 // won't compile!
```

This may not feel right to you at first.  Why on earth would you store your state in a value that can't change?  Turns on - this is a very good thing.  If you consider multi-threaded code - one of the biggest sources of bugs results from more than one thread trying to change a value's state at the same time.  If you store your state in an immutable manner - then you can never run into this bug!  If you adopt this concept from the start then it makes it very easy to write code that can scale up and be used in a multi-threaded environment.

In general - Scala prefers `val` to `var` 99% of the time.  Know that `var` exists - but always use `val`.

But how would you change a value then if its immutable?  There are a few patterns to do this that we will explore later.

## Session 2 - Operators
Good news - if you know your operators from another language, they pretty much all apply here:

```scala
1 + 2

1.+(2)

1 * 2

1 % 2

true && false

true || false

var i = 0

i += 1

println(i)
```

Its important to point out that the increment operator `+=` only work if you are using a variable.

## Session 3 - Control Your Flow
Flow control is very similar to other languages.  With one catch - they all result in a value.  Your basic `if`, `for` and `while` exist.  There's even a new flow control mechanism in pattern matching via the `match` keyword.  Let's take a look at how they all behave.

### `if` Examples
```scala

val age = 42

if (age < 55) {
  println("No AARP Discount")
} else {
  println("AARP discount!!")
}

val nothing =
  if (age < 55) {
    println("No AARP Discount")
  } else {
    println("AARP discount!!")
  }

val aarpDiscount = (age < 55)

val percentDiscount = if (aarpDiscount && age < 65) 10 else 15
```

Please note how you the `if` returns a value from whichever branch evaluates to true.  Pretty handy.  As such there is no ternary operator in Scala - just use `if`.

### `while` Examples
```scala
var x = 10

while (x <= 10) {
  println(x)
  x -= 1
}

// do..while

do {
  println(x)
  x -=1
} while (x >= 10)

```
Pretty straight forward.


### `for` Examples
```scala
for (x <- 1 to 4) {
  val squared = x * x
  println(squared)
} 

for (x <- 1 until 4) {
  val squared = x * x
  println(squared)
}
```
Note the `to` and `until` functions. Both of these will produce a range of integers starting with the first value and ending with the last.  The difference between the two is `to` is inclusive and `until` is not.  Run them to see the difference in action.

## Session 4 - Functions & Methods
In Scala you have two ways to organize your code.  You can define methods or you can define functions.  The difference between the two is that methods are not values by default. (You can however, coerce a method into a function using `_`).  Functions are first class values in Scala.  So there are definite benefits to writing functions over methods.

Methods are defined using the `def` keyword.  This is followed by an identifier, a parameter list, the return value type annotation and then the function implementation itself.

```scala
def aarpDiscount(age: Int): Int = {
  val NO_DISCOUNT = 0
  val LEVEL_1_DISCOUNT = 10
  val LEVEL_2_DISCOUNT = 15
  if (age < 55) {
    NO_DISCOUNT
  } else if (age > 55 && age < 65) {
    LEVEL_1_DISCOUNT
  } else {
    LEVEL_2_DISCOUNT
  }
}

aarpDiscount(42)
aarpDiscount(57)
aarpDiscount(75)
```

**NOTE** In Scala - like Java - all parameter values are pass by value.  Thus they are immediately evaluated.  Scala has another more advanced option that we'll discuss later called pass by name.  Its used with higher ordered functions and will defer evaluation of the function.

### Named Parameters and Default Values
In Scala parameters are named.  You can also set default values for parameters.  The combination of this allows you to setup situations where you only need to provide some of the paramters for a function but not all. For example:

```scala
def myFunction(param1: String, optionalParam: Int = 10, param3: Long) = ???

myFunction(param1 = "Hi", param3 = 10L)
```

### Functions
Functions, while very similar to methods are defined a bit differently.  Type annotations for functions are in the form of `<Parameter> => <Return Type>`.  The function body itself is then defined using the `=>` symbol: `(parameter: <Type>, ...) => <function body>`.  Multiple parameters can be supplied, as long as the type annotation defines them.

```scala
val qualifyForDiscount: Int => Boolean = (age: Int) => (age >= 55)

qualifyForDiscount(42)
```

Remember - all functions are first class values in Scala.  Behind the scenes, `qualifyForDiscount()` as shown above will evaluate to an object of type `Function1[Int, Boolean]` because it accepts one `Int` parameter and returns an `Boolean` value.

## Session 5 - Exceptions
As mentioned earlier like Java and C# - Scala can signal program errors via Exceptions.  However, unlike Java, Scala only has RuntimeExceptions.  There are no checked exceptions.  This is another indication that Scala's functional side perfers a different way to handle errors than its OO imperative cousins.

So how do Scala's exceptions work?  Fairly similarly to Java's - with the exception that they are RuntimeExceptions.

```scala
def discount(age: Int): Int = {
  val DISCOUNT = 10
  if (age >= 55) {
    DISCOUNT
  } else {
    throw new IllegalArgumentException("You must be 55 or older for the discount!")
  }
}

try {
  discount(42)
} catch {
  case ilae: IllegalArgumentException => println(s"Error asking for discount: ${ilae.getMessage}")
}
```

Notice its generally the same pattern.  You can use `try/catch` if you'd like to trap exceptions - though this usage is generally not used.  You would use the `Try` monad instead. (Wait?  What's a monad??  Don't worry about that term yet - just know that its a special type of object in Scala)  

Note the `catch` expression is actually a pattern match.  We haven't discussed pattern matches - but here you could potentially match against multiple exceptions that could arise from your method here.

## Session 6 - Collections
Another very common thing in programming is working with collections of values.  Here in Scala every collection is an object - and there are both mutable and immutable collections.  We're going to look at some common ones and demonstrate usage.  Its worth digging deeper into the Scaladoc for the collections - there are a lot of very useful methods on them for common tasks such as `sum` and `reverse`.

**NOTE** All collections in Scala 2.13.x are homogenous.  That means the type that is defined for them is what they contain.  You cannot mix/match values with different types.  Scala 3.x does introduce heterogenous collections.  If you need that sort of functionalty with Scala 2.13.x you can use a library called `shapeless`.

```scala
val names: Seq[String] = Seq("Elliot", "Dom", "Angela")

names(1)

names.head

names.tail

names.foreach(println)

names.find(_ == "Dom")

val quantities = List(23, 35, 25)

quantities.sum

quantities.map(_ * 2)

names.zipWithIndex

val quantitiesWithName: Seq[(String, Int)] = names.zip(quantities)

val quantityByNameMap = quantitiesWithName.toMap

println(s"Walter's contribution: ${quantityByNameMap("Angela")}")

val ages: List[Int] = 24 :: 35 :: 25 :: Nil

```

Here we demonstrate the `Seq[A]` - a sequence of values of type `A`, and `List[A]`.  Compared to Java - a `Seq[A]` is equivalent to a Java List; a `List[A]` is equivalent to a Java LinkedList.

There are also `Map[K,V]`, `Set[A]`, `Array[A]` and mutable and immutable versions of each.  To learn more please see: https://docs.scala-lang.org/overviews/collections-2.13/introduction.html


## Session 7 - Traits, Classes, Objects and Tuples
Eventually, you're going to want to organize your code and map it to your domain model.  So how do you do that in Scala?  Thankfully you have a handful of options at your disposal.  Let's start with the basics: traits, classes and objects.  You'll notice that right off the bat, there are different options than you have in Java or C#.  Let's discuss.

### Traits
Traits are *almost* the equivalent of an interface in Java.  

The main differences are that traits in Scala can...
* provide a default implementation for a given interface method
* contain state
* operate as a 'mix-in' to extend functionality of any other class

Here's a pretty simple trait that behaves mainly as an interface - it provides a `fullName` method.

```scala
trait FullName {
  def fullName: String
}
```

We can extend that trait with our `Person` class - we just have to implement the `fullName` method - or declare it `abstract`.

```scala

class Person(age: Int, firstName: String, lastName: String) extends FullName {
  override def fullName: String = s"$firstName $lastName"
}
```
Unless otherwise specified - properties on a class are values.  You can define them as variables if you prefer - but you have to specify this with the `var` keyword.

Also unique to Scala are `object`s.  An `object` is simply a singleton in Scala.  All methods defined on an `object` are considered static.
```scala
object Person {
  def apply(age: Int, firstName: String, lastName: String) = new Person(age, firstName, lastName)

  def printFullName(fullNamed: FullName): Unit = println(s"${fullNamed.fullName}")
}
```
There are some other special properties `object`s contain.  First, if the object has the same name as a trait or class, it is considered a 'companion' object.  This means that it has special access to all properties/methods on an instance of a class or trait with the same name.  Second you'll notice that we have a method named `apply` - this is a special name. An `apply` method can take in any number of parameters, but must return the companion class type.  You can think of these as a way to provide alternate constructor parameters for an instance.  A way to ease use as well - as you do not have invoke the `new` keyword if you use the apply method.  But how do you invoke an apply method?  Simply by calling the object name followed by the parameters that match the apply method. For example:

`Person(42, "Foo", "Bar")` would invoke the `apply` method above.  You can have multiple apply methods.  These also are important when it comes to pattern matching - as well as a matching `unapply` method - which you guessed it - disassembles a given class into a tuple of all the parameter values.

### Usage
Let's use the objects we defined above...

```scala
val elliot = new Person(24, "Elliot", "Alderson")

println(s"${elliot.fullName}")

val angela = Person(25, "Angela", "Moss")

Person.printFullName(angela)

val angela2 = Person(25, "Angela", "Moss")

angela == angela2

//elliot.age = 43
```

### Tuples
Scala also provides another way to group values together in a fixed order, each with their own type - tuples.  Tuples are immutable and can be used to return multiple values from a function.  Tuples are values and thus have an associated class with them.

```scala
val ingredient = ("Sugar", 25)
println(ingredient._1) // Sugar
println(ingredient._2) // 25
```

The type here would be `(String, Int)`, which could also be known as `Tuple2[String, Int]`.  Extracting values from a tuple is easy - just access them by position `_1`.

You can pattern match on tuples.

Tuples are handy when working within blocks of code where perhaps things are small enough not to warrant a class.  However be cautious using them outside of a class or code block as they can get difficult to work with - and are not very readable.  For example - `ingredient._1` doesn't read as well as `Ingredient.name`.  If you want an easier way to model things like tuples, but want some benefits from classes, then you probably want what we're going to talk about next - a `case class`.



## Session 8 - Case Classes (Algebraic Data Types)
Another unique structure option for you in Scala is what's called a `case class`.  Newer versions (JDK 15+) of Java have what are called `records` - and while similar case classes have some differences.  (For info on the similarities/differences - see this [blog on the topic](https://blog.softwaremill.com/java-15-through-the-eyes-of-a-scala-programmer-edde1ea04492#:~:text=records%20cannot%20extend%20classes%2C%20as%20they%20already%20implicitly,case%20classes%20can%20%28though%20this%20has%20limited%20utility%29).

In scala a `case class` effectively represents an algebraic data type.  It sounds scary - but basically its a simple datatype that is fully immutable and provides you with implementations of `hashCode`, `equals`, `toString`, `apply`, `unapply` and more.  They're very helpful when pattern matching as a result!  It is important to note that generally speaking - while nothing will stop you from building an inheritance tree with a `case class` - its probably not going to work the way you want it to, so don't try.  You can however, extend and implement `trait`s and that's something that's commonly done.  When modelling data with a case class you prefer composition over inheritance.

Oh yeah - did I mention case classes are immutable?  Then how on earth are you supposed to change values contained within?  Well you don't ever do that - technically.  You instead use the `copy` method - which will be generated for you and contain a named parameter for every property on your case class.  That will hand you a new instance of a case class that has every value the original had except for those you specified to change in the `copy` method.  Easy!

Here's an example:

```scala
case class Person(age: Int, firstName: String, lastName: String)
object Person {
  def fullName(p: Person): String = s"${p.firstName} ${p.lastName}"
}

val angela = Person(25, "Angela", "Moss")

Person.fullName(angela)

angela.age = 46 // can't do this - immutable!

val updatedAngela = angela.copy(age = 46)

angela == updatedAngela

val angela2 = Person(25, "Angela", "Moss")

angela == angela2
```

Further Reading - I've mentioned the term alegbraic data type a few times but haven't really explained them.  That's because the details can be gory.  If you'd like to learn more, try this link: https://alvinalexander.com/scala/fp-book/algebraic-data-types-adts-in-scala/.  Another case of FP having many scary terms, but the actual usage is pretty simple when you view it from that perspective.

## Session 9 - Pattern Matching

Pattern matching is a very useful feature of Scala.  While it looks similar to a `switch...case` statement in Java, its quite different.  (**NOTE** JDK 17 has an experimental feature that attempt to add [pattern matching](https://docs.oracle.com/en/java/javase/17/language/pattern-matching.html) to the Java - but its still not quite the same as it is in Scala)  Pattern matching essentially simply tries to match a value against pattern.

Let's look at some simple examples:


```scala
val name = "Elliot"

name match {
  case "Dom" => println("FBI Agent")
  case "Elliot" => println("1337 h4x0r")
  case "Angela" => println("Corporate Exec")
  case _ => println("I don't know who this is!")
}
```

Here we simply want to match the `name` value against a set of patterns, each of which is simple a potential result of the value.  If we match the pattern, we then execute the code to the right of the `=>`.  Note that we have our `_` wildcard here - which acts as the default case if none of the previous patterns matched the value.

Pretty simple - you can do this with about any value.  But at the end of the day, this in of itself isn't very useful.

### Pattern Matching Case Classes
This gets a bit more interesting when you pattern match with a `case class`.  Remember we said earlier when discussing case classes that by nature of using them, you get a bunch of methods implemented 'for free' that help enable pattern matching?  Here's an example of what you can do with them:

```scala
case class Person(age: Int, firstName: String, lastName: String)

val elliot = Person(24, "Elliot", "Alderson")
val angela = Person(25, "Angela", "Moss")
val dom = Person(35, "Dom", "DiPierro")

def describe(person: Person): Unit = {
  person match {
    case Person(_, firstName, lastName) if (firstName == "Elliot") => println(s"$firstName is the narrator - can you trust him?")
    case Person(age, _, _) if (age < 30) => println("A young activist.")
    case p: Person => println(s"We have: ${p.firstName} here.")
  }
}

describe(elliot)
describe(angela)
describe(dom)
```

As you can see, there's a few more tricks you can pull with a pattern match.  First off, given a case class you can match against any of the properties of the case class.  You can map these to variables or if you don't care about a particular value you can wildcard it with `_`.  Note that you must match every property of a case class otherwise you'll get a compilation error.  

Also note that you can apply a filter to a pattern - and you can reference pattern variables that you defined in it as well.  If the filter fails the match, the next pattern is tried.

### Sealed Traits and Pattern Matching
Let's kick it up a notch - lets talk about sealed traits.  A sealed trait is a trait that can only have its definitions in the same file of which it is defined.  That doesn't sound very helpful at first if you think of it from an OO perspective.  This doesn't let others extend your trait.  But in the FP world, you may actually want to do this when domain modelling.  Sealed traits look like this:

```scala

sealed trait Animal 
case class Cat(name: String, lives: Int) extends Animal
case class Dog(name: String, milkbonesConsumed: Int) extends Animal
```

This defines a sealed trait named `Animal` with two case classes that extend it - `Cat` and `Dog`.

Now we can do this:

```scala
val animal: Animal = Dog(2)

animal match {
  case Cat(name, lives) => println(s"$name has $lives lives left!")
  case Dog(name, milkbones) => println(s"$name has consumed $milkbones milkbones!")
}
```

In Java - to do something similar - you'd wind up casting or using `instanceOf` to compare types and get at what you need.  No need to do that with pattern matching!  Also - by nature of having `Animal` as a sealed trait - the compiler knows all the possible types that extend it - and as such will warn you if you don't match against all possibilities.  Again - potentially reducing bugs in your code!

**NOTE** We'll talk more about sealed traits when we re-visit Algebraic Data Types a bit more closely later on.


### Pattern matching Lists
You can also pattern match with `List[A]` as well, which can be handy when processing a list recursively.  This leverages the `cons` operator  - `::`.  

```scala

def listMatch(names: List[String]): Unit = {
  names match {
    case head :: tail =>
      println(s"$head - $tail")
      listMatch(tail)
    case Nil => println("We are done.")
  }
}


val theGang = List("Elliot", "Angela", "Dom", "Mr Robot")

listMatch(theGang)

```

Further Reading - https://docs.scala-lang.org/tour/pattern-matching.html


## End of Session
If you've gotten this far - congrats!  You know know the basics of the Scala language.  But of course, there's much, much more to learn.  Continue onto lesson 102 - Advanced Scala to expand your knowledge.