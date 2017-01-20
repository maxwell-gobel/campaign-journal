package com.synaptichshock.campaign_journal.commands

import freasymonad.scalaz.free
import org.neo4j.driver.v1._

import scalaz.Id.Id
import scalaz.{Free, \/}

@free trait Neo4j {
  type Neo4jOp[A] = Free[GrammarADT, A]
  sealed trait GrammarADT[A]

  def executeInTransaction[A](cypher: Statement)(extractor: StatementResult => A): Neo4jOp[Throwable \/ A]
}

object Neo4jInterpreters {

  def executor(sessionCreator: () => Session): Neo4j.Interp[Id] = new Neo4j.Interp[Id] {
    override def executeInTransaction[A](cypher: Statement)(extractor: StatementResult => A): Throwable \/ A = {
      val session = sessionCreator()
      val result = \/.fromTryCatchNonFatal{
        extractor(session.run(cypher))
      }
      session.close()
      result
    }
  }
}