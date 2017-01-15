name := "CampaignJournal"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Libraries.http4s

enablePlugins(JavaServerAppPackaging, AshScriptPlugin)

packageName in Docker := "campaign-journal"
version in Docker := "1.0"

dockerBaseImage := "frolvlad/alpine-oraclejdk8:slim"