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