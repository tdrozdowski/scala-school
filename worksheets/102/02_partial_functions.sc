val badDivide = (x: Int) => 42 / x

badDivide(2)
//badDivide(0)

val divide = new PartialFunction[Int, Int] {
  def apply(x: Int): Int = 42 / x
  def isDefinedAt(x: Int): Boolean = x != 0
}

divide.isDefinedAt(2)

if (divide.isDefinedAt(2)) divide(2)

divide.isDefinedAt(0)

val divide2: PartialFunction[Int, Int] = {
  case d: Int if d != 0 => 42 / d
}

divide2(2)
//divide2(0)

// converts 1 to "one", etc., up to 5
val convert1to5: PartialFunction[Int, String] = new PartialFunction[Int, String] {
  val nums: Array[String] = Array("one", "two", "three", "four", "five")
  def apply(i: Int): String = nums(i-1)
  def isDefinedAt(i: Int): Boolean = i > 0 && i < 6
}

// converts 6 to "six", etc., up to 10
val convert6to10: PartialFunction[Int, String] = new PartialFunction[Int, String] {
  val nums: Array[String] = Array("six", "seven", "eight", "nine", "ten")
  def apply(i: Int): String = nums(i-6)
  def isDefinedAt(i: Int): Boolean = i > 5 && i < 11
}

val handle1to10: PartialFunction[Int, String] = convert1to5.orElse(convert6to10)

handle1to10(3)
handle1to10(8)

val numbers = List(3,5,6,8,0,9)
numbers.map(divide2)
numbers.collect(divide2)