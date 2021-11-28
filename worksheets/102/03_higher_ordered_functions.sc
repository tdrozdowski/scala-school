val salaries = Seq(20000, 70000, 40000)
val newSalaries = salaries.map(x => x * 2)

val newSalaries = salaries.map(_ * 2)

val salaries = Seq(20000, 70000, 40000)
val salaryDoubler = (x: Int) => x * 2
val newSalaries = salaries.map(salaryDoubler)
val moreSalaries = Seq(90000, 80000, 35000)
val moreNewSalaries = salaries.map(salaryDoubler)

object SalaryRaiser {

  // basic boss
  def smallPromotion(salaries: Seq[Double]): Seq[Double] =
    salaries.map(salary => salary * 1.1)
  // nice boss
  def greatPromotion(salaries: Seq[Double]): Seq[Double] =
    salaries.map(salary => salary * math.log(salary))
  // awesome boss!
  def hugePromotion(salaries: Seq[Double]): Seq[Double] =
    salaries.map(salary => salary * salary)
}

val salaries = Seq(20000.00, 70000.00, 40000.00)
SalaryRaiser.greatPromotion(salaries)

object SalaryRaiser {

  private def promotion(salaries: Seq[Double], promotionFunction: Double => Double): Seq[Double] =
    salaries.map(promotionFunction)

  def smallPromotion(salaries: Seq[Double]): Seq[Double] =
    promotion(salaries, salary => salary * 1.1)

  def greatPromotion(salaries: Seq[Double]): Seq[Double] =
    promotion(salaries, salary => salary * math.log(salary))

  def hugePromotion(salaries: Seq[Double]): Seq[Double] =
    promotion(salaries, salary => salary * salary)
}

val salaries = Seq(20000.00, 70000.00, 40000.00)
SalaryRaiser.hugePromotion(salaries)