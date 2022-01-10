import io.getquill._

implicit val ctx: SqlMirrorContext[MirrorSqlDialect.type, Literal.type] = new SqlMirrorContext(MirrorSqlDialect, Literal)

import ctx._

val pi = quote(3.14159)

/*
case class Circle(radius: Float)

val areas = quote {
  query[Circle].map(c => pi * c.radius * c.radius)
}

/*
val area = quote {
  (c: Circle) =>
    val r2 = c.radius * c.radius
    pi * r2
}

val areas = quote {
  query[Circle].map(c => area(c))
}

/*
def existsAny[T] = quote {
  (xs: Query[T]) => (p: T => Boolean) => xs.filter(p(_)).nonEmpty
}

val q = quote {
  query[Circle].filter { c1 => existsAny(query[Circle])(c2 => c2.radius > c1.radius) }
}

ctx.run(q)
/*
// Avoid type widening (Quoted[Query[Circle]]), or else the quotation will be dynamic.
val q: Quoted[Query[Circle]] = quote {
  query[Circle].filter(c => c.radius > 10)
}

ctx.run(q) // Dynamic query
/*
// do you lift, bro?

def biggerThan(i: Float) = quote {
  query[Circle].filter(r => r.radius > lift(i))
}
ctx.run(biggerThan(10))


/*
def find(radiusList: List[Float]) = quote {
  query[Circle].filter(r => liftQuery(radiusList).contains(r.radius))
}
ctx.run(find(List(1.1F, 1.2F)))

def insert(circles: List[Circle]) = quote {
  liftQuery(circles).foreach(c => query[Circle].insert(c))
}
ctx.run(insert(List(Circle(1.1F), Circle(1.2F))))

/*

case class CircleRecord(radius: Float)

val circles = quote {
  querySchema[CircleRecord]("circles", _.radius -> "radius_column")
}

val q = quote {
  circles.filter(c => c.radius > 1)
}

ctx.run(q)
/*
case class Product(id: Int, description: String, sku: Long)

val q = quote {
  query[Product].insert(lift(Product(0, "My Product", 1011L))).returningGenerated(_.id)
}

val returnedIds = ctx.run(q)
/*
case class Person(id: Int, name: String, age: Int)
case class Contact(personId: Int, phone: String)

val q = quote {
  for {
    p <- query[Person] if(p.id == 999)
    c <- query[Contact] if(c.personId == p.id)
  } yield {
    (p.name, c.phone)
  }
}

ctx.run(q)
/*
val str = ctx.translate(query[Person].insert(lift(Person(0, "Joe", 45))))
println(str)

*/