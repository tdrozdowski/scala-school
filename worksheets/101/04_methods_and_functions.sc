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