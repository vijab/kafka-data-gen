organization := "com.vijai"

name := "kafka-data-gen"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.8"

val akkaVersion = "2.5.13"
val kafkaVersion = "1.1.1"
val circeVersion = "0.10.0"

libraryDependencies += "com.typesafe.akka" %% "akka-stream" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-stream-kafka" % "0.22"
libraryDependencies += "org.apache.kafka" % "kafka-clients" % kafkaVersion
libraryDependencies += "com.typesafe" % "config" % "1.3.4"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

// Circe

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)