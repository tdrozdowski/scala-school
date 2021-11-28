// if
val age = 42

//if (age < 55) {
//  println("No AARP Discount")
//} else {
//  println("AARP discount!!")
//}
//
//// what do you think we get here?
//val nothing =
//  if (age < 55) {
//    println("No AARP Discount")
//  } else {
//    println("AARP discount!!")
//  }
//
//// ... or here?
//val aarpDiscount = (age < 55)
//// ... and even here?
//val percentDiscount = if (aarpDiscount && age < 65) 10 else 15*/

// while
var x = 10

while (x >= 10) {
  println(x)
  x -= 1
}

x = 5

do {
  println(x)
  x -=1
} while (x >= 10)

// for
for (x <- 1 to 4) {
  val squared = x * x
  println(squared)
}

for (x <- 1 until 4) {
  val squared = x * x
  println(squared)
}