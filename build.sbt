name := "CampaignJournal"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Libraries.http4s
libraryDependencies += Libraries.sl4fj
libraryDependencies += Libraries.neo4j
libraryDependencies += Libraries.freasyMonad

resolvers += Resolver.sonatypeRepo("releases")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

enablePlugins(JavaServerAppPackaging, AshScriptPlugin)

packageName in Docker := "campaign-journal"
version in Docker := "1.0"

dockerBaseImage := "frolvlad/alpine-oraclejdk8:slim"