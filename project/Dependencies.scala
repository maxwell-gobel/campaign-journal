import sbt._

object Versions {
  val http4s = "0.15.2"
}

object Libraries {
  val http4s: Seq[ModuleID] = Seq(
    "http4s-dsl",
    "http4s-blaze-server",
    "http4s-blaze-client"
  ).map("org.http4s" %% _ % Versions.http4s)
}