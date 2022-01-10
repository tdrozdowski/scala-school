ThisBuild / name := "scala-school"
ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.13.7"
ThisBuild / testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
ThisBuild / scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Ymacro-annotations")

libraryDependencies ++= Seq(
  "dev.zio"              %% "zio"            % "1.0.13",
  "dev.zio"              %% "zio-macros"     % "1.0.13",
  "io.github.kitlangton" %% "zio-magic"      % "0.3.11",
  "io.getquill"          %% "quill-jdbc-zio" % "3.12.0"
)
