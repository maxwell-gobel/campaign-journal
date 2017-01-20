package com.synaptichshock.campaign_journal.config

import com.synaptichshock.campaign_journal.commands.Neo4j
import com.synaptichshock.campaign_journal.commands.Neo4jInterpreters.executor
import org.neo4j.driver.v1.Driver

import scalaz.Id.Id

trait ApplicationConfig {
  def neo4jDriver: Driver
  lazy val neo4jExecutor: Neo4j.Interp[Id] = executor(() => this.neo4jDriver.session())
}
