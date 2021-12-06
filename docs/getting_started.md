## A Hybrid Language
The Java Virtual Machine (JVM) has been around for many years (almost 30!) and thus has a ton of great optimizations and ecosystems built around it.  There are many, many benefits of choosing it as your platform. But Java itself feels like a 30 year old language.  Java is an Object Oriented (OO), imperative programming language.  While its widely taught and used around the world - it does have some limitations.  Due to its imperative nature writing modern cloud native applications can tend to be bug prone.  Tons of boilerplate and difficult to master concurrency libraries lead to a lot of this.

So if you want to leverage the JVM but want a more modern, less bug prone type-safe way to write your code?  Scala could be the answer.  While Scala itself is not new, the language is undergoing a bit of a renaissance with version 3.x recently being released.  It has a solid place in the big data space and is making in-roads into the microservices realm as well.  Its heavily used by large internet properities who need to scale and scale BIG. (i.e. Twitter)  What is Scala?  Scala is a hybrid language, it is both object oriented but also functional.  This makes it easy for developers to transition over from traditional OO languages into the world of functional programming.

This version of Scala School covers Scala 2.x - specifically version 2.13.x.  This is the last version of the 2.x series and the one most commonly used in production scenarios.  At the time of this writing, Scala 3.1.x has been released - and it introduces a LOT of changes.  The good news is that its compatible with what you'll learn here with Scala 2.13.  Also at the time of this writing - the IDE tooling for Scala 3.x has not quite matured.  After it has matured, I would expect a much broader adoption of Scala 3 - and this course will be updated as such.  Any time we cover a topic that will change significantly for Scala 3 we will note it.

## Getting Started
To get started with Scala, you should have the following installed at a minimum:

* JDK 17 - we recommend the latest Eclipse Temurin OpenJDK build at http://adoptium.net
* IntelliJ Community Edition (or another editor that has support for Scala) - version 2021.2.3 is the most recent at the time of this writing.  Be sure to enable the Scala Plugin during installation.  We recommend installing IntelliJ via the JetBrains Toolbox.  This will help you keep your IntelliJ up to date and manage all the files for you. - https://www.jetbrains.com/toolbox-app/
* SBT - https://www.scala-sbt.org