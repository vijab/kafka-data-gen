package com.vijai.data.commons

import scala.util.Random

object CustomerListGenerator {

  private[this] lazy val TWO_YEARS_IN_MS = 2 * 12 * 30 * 24 * 60 * 60 * 1000

  def generateRandomList(location: String, count: Int): CustomerList = {
    val customers = (0 until count) map{i =>
      Customer(name = Random.alphanumeric.take(5).mkString(""),
        age = Random.nextInt(100),
        amount = Random.nextInt(10000).toDouble,
        createdOn = System.currentTimeMillis() - Math.abs(Random.nextInt(TWO_YEARS_IN_MS))
      )
    }
    CustomerList(customers = customers, location = location, createdOn = System.currentTimeMillis())
  }

}
