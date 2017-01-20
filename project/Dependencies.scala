import sbt._

object Versions {
  val http4s = "0.15.2a"
  val slf4j = "1.7.22"
  val neo4j = "1.1.0"
  val freasyMonad = "0.5.0"
}

object Libraries {
  val http4s: Seq[ModuleID] = Seq(
    "http4s-dsl",
    "http4s-blaze-server",
    "http4s-blaze-client"
  ).map("org.http4s" %% _ % Versions.http4s)

  val sl4fj: ModuleID = "org.slf4j" % "slf4j-simple" % Versions.slf4j

  val neo4j: ModuleID = "org.neo4j.driver" % "neo4j-java-driver" % Versions.neo4j

  val freasyMonad: ModuleID = "com.github.thangiee" % "freasy-monad_sjs0.6_2.12.0-RC2" % Versions.freasyMonad
}