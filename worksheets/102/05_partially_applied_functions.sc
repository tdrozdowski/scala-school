def multiply(a: Int)(b: Int) = a * b

val double = multiply(2)(_)

double(4)

def multiply(a: Int, b: Int) = a * b
val multiplyFunction = multiply _

val multiplyCurried = (multiply _).curried
multiplyCurried(4)(3)

val doubleCurried = multiplyCurried(2)
doubleCurried(5)

val triple = multiply(3, _: Int)

triple(10)