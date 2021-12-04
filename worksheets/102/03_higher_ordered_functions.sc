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

// return a function
def urlBuilder(ssl: Boolean, domainName: String): (String, String) => String = {
  val schema = if (ssl) "https://" else "http://"
  (endpoint: String, query: String) => s"$schema$domainName/$endpoint?$query"
}

val domainName = "www.example.com"
def getURL = urlBuilder(ssl=true, domainName)
val endpoint = "users"
val query = "id=1"
val url = getURL(endpoint, query) // "https://www.example.com/users?id=1": String

// thunks
// Our own if/then/else
def when[A](test: Boolean, whenTrue: A, whenFalse: A): A =
  test match {
    case true  => whenTrue
    case false => whenFalse
  }

when(1 == 2, "foo", "bar")
when(1 == 1, "foo", "bar")
when(1 == 1, println("foo"), println("bar"))

def when[A](test: Boolean, whenTrue: => A, whenFalse: => A): A =
  test match {
    case true  => whenTrue
    case false => whenFalse
  }

when(1 == 1, println("foo"), println("bar"))
when(1 == 2, println("foo"), println("bar"))