val names: Seq[String] = Seq("Elliot", "Dom", "Angela")

names(1)

names.head

names.tail

names.foreach(println)

names.find(_ == "Dom")

val quantities = List(23, 35, 25)

quantities.sum

quantities.map(_ * 2)

names.zipWithIndex

val quantitiesWithName: Seq[(String, Int)] = names.zip(quantities)

val quantityByNameMap = quantitiesWithName.toMap

println(s"Walter's contribution: ${quantityByNameMap("Angela")}")

val ages: List[Int] = 24 :: 35 :: 25 :: Nil