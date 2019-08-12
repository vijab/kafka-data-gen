package com.vijai.data.commons

import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

object TestGenerator extends App {

  val customerList = CustomerListGenerator.generateRandomList("berlin", 10)

  println(customerList.asJson.noSpaces)

}
