ThisBuild / name := "scala-school"
ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "3.1.1"
ThisBuild / testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
ThisBuild / scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Ymacro-annotations")

val dependencies = Seq(
  "dev.zio"       %% "zio"                 % "1.0.13",
  "dev.zio"       %% "zio-macros"          % "1.0.13",
  "dev.zio"       %% "zio-config"          % "2.0.0",
  "dev.zio"       %% "zio-config-magnolia" % "2.0.0",
  "dev.zio"       %% "zio-config-typesafe" % "2.0.0",
  "dev.zio"       %% "zio-logging-slf4j"   % "0.5.14",
//  ("io.github.kitlangton" %% "zio-magic"           % "0.3.11").cross(CrossVersion.for3Use2_13),
  "io.getquill"   %% "quill-jdbc-zio"      % "3.17.0-RC1",
  "org.flywaydb"   % "flyway-core"         % "7.9.1",
  "org.postgresql" % "postgresql"          % "42.2.22"
)

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    Defaults.itSettings,
    libraryDependencies ++= dependencies
  )
