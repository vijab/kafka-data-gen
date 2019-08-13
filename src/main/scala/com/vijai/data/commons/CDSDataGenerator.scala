package com.vijai.data.commons

import scala.util.Random

object CDSDataGenerator extends App {

  def randomCdsMessage(seed: Int): CDSRecord = {
    val risk: Boolean = if(seed % 10 == 0) false else true
    val expertRulesArray: Array[Long] = risk match {
      case true => {
        (0 until Math.abs(Random.nextInt(10))).map(_.toLong).toArray
      }
      case false => Array()
    }
    CDSRecord(
      scoringId = System.currentTimeMillis(),
      scoringData = Math.abs(new Random().nextInt(10000)),
      contractNumber = Math.abs(new Random().nextInt(10000)),
      personId = Math.abs(new Random().nextInt(1000)),
      riskRelevant = risk,
      expertRules = expertRulesArray
    )
  }

}
