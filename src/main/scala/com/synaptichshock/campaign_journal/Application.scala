package com.synaptichshock.campaign_journal

import com.synaptichshock.campaign_journal.config.ApplicationConfig
import com.synaptichshock.campaign_journal.web.CampaignService
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.server.{Server, ServerApp}
import org.neo4j.driver.v1.{AuthTokens, Driver, GraphDatabase}

import scalaz.concurrent.Task

/**
  * Created by maxwell on 1/15/2017.
  */
object Application extends ServerApp {

  override def server(args: List[String]): Task[Server] = {
    val config: ApplicationConfig = new ApplicationConfig {
      override def neo4jDriver: Driver = GraphDatabase.driver("bolt://192.168.99.100:7687", AuthTokens.basic("neo4j", "password"))
    }

    BlazeBuilder
      .bindHttp(8080, "0.0.0.0")
      .mountService(CampaignService(config).endpoints, "/")
      .start
  }
}
