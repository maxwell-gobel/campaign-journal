package com.synaptichshock.campaign_journal.web

import com.synaptichshock.campaign_journal.commands.Neo4j.ops._
import com.synaptichshock.campaign_journal.config.ApplicationConfig
import org.http4s._
import org.http4s.dsl._
import org.neo4j.driver.v1.Statement

import scala.collection.JavaConverters._
import scalaz.{-\/, Reader, \/, \/-}

case class CampaignService(config: ApplicationConfig) {

  val endpoints = HttpService {
    case GET -> Root / "campaigns" => Ok(getCampaigns(config) match {
      case \/-(strings) => strings.reduce(_ + ", " + _)
      case -\/(error) => error.getMessage
    })
  }

  private def getCampaigns: Reader[ApplicationConfig, Throwable \/ List[String]] = Reader((config: ApplicationConfig) => {
    val stmt = new Statement("match (c: Campaign) return c")
    val program = for {
      result <- executeInTransaction(stmt) {
        _.list[String] { row =>
          row.values().asScala.toList.ma
          row.get("name").asString()
        }.asScala.toList
      }
    } yield result
    config.neo4jExecutor.run(program)
  })

}