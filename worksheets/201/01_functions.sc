import scala.util.Random

// pure
val multiply: (Int, Int)=> Int = (a, b) => a * b
val multiplyCurried = multiply.curried
val double = multiplyCurried(2)

double(5)

// impure
val multiplyDebug: (Int, Int) => Int = (a,b) => {
  val result = a * b
  println(s"Params: ($a, $b) => result: $result")
  result
}

// also impure
Random.nextInt(10)

multiplyDebug(2,5)