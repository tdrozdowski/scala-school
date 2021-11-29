import scala.annotation.tailrec

def sum(values: List[Int]): Int = values match {
  case Nil => 0
  case head :: tail => head + sum(tail)
}

val numbers: List[Int] = List(1,2,3,4,5)
println(s"The total is: ${sum(numbers)}")

//val aLotOfNumbers = List.range(1,100000)
//val x = sum(aLotOfNumbers)

def sum(values: List[Int]): Int = {
  @tailrec
  def sumWithAccumulator(values: List[Int], accum: Int): Int = values match {
    case Nil => accum
    case head :: tail => sumWithAccumulator(tail, accum + head)
  }
  sumWithAccumulator(values, 0)
}

val aLotOfNumbers = List.range(1,100000)
val x = sum(aLotOfNumbers)
println(s"The total is: $x")