package com.vijai.data.kafka

import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger
import com.vijai.data.commons.{CustomerList, CustomerListGenerator}
import io.circe.Json
import io.circe.generic.auto._
import io.circe.syntax._
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.{ByteArraySerializer, StringSerializer}

import scala.concurrent.duration.DurationLong
import scala.util.Random

object KafkaDataGenerator extends App {

  implicit val actorSystem = ActorSystem("SimpleStream")
  implicit val actorMaterializer = ActorMaterializer()

  lazy val conf = ConfigFactory.defaultApplication()
  lazy val LOCATION_COUNT = 10

  val logger = Logger("com.vijai.data.kafka.KafkaDataGenerator")

  val bootstrapServers = conf.getString("app.bootstrap.servers")
  val kafkaTopic = conf.getString("app.producer.topic.name")

  val producerSettings = ProducerSettings(actorSystem, new ByteArraySerializer, new StringSerializer)
    .withBootstrapServers(bootstrapServers)

  def startProducing() = {
    logger.info(s"Using bootstrap server, $bootstrapServers and topic $kafkaTopic")
    Source.tick(0 seconds, 5 seconds, ())
      .map(_ => {
        val value = Random.nextInt(50) match {
          case 2 => Json.Null.noSpaces
          case _ => {
            val customerList: CustomerList = CustomerListGenerator.generateRandomList(s"LOCATION_${Random.nextInt(LOCATION_COUNT)}", Random.nextInt(20))
            logger.info(s"Producing bulk message with - ${customerList.customers.size} customers.")
            customerList.asJson.noSpaces
          }
        }
        new ProducerRecord[Array[Byte], String](kafkaTopic, value)
      })
      .runWith(Producer.plainSink(producerSettings))
  }

  startProducing

}
