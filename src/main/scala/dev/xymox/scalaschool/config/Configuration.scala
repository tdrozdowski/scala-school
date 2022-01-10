package dev.xymox.scalaschool.config

import dev.xymox.scalaschool.service.migration.MigrationConfig
import zio.{Has, ULayer}
import zio.config._
import zio.config.magnolia._
import zio.config.syntax._
import zio.config.typesafe.TypesafeConfig

object Configuration {
  case class AppConfig(migration: MigrationConfig)

  implicit val appConfigDescriptor: ConfigDescriptor[AppConfig] = descriptor[AppConfig].mapKey(toKebabCase)

  val live: ULayer[Has[AppConfig]] =
    TypesafeConfig.fromDefaultLoader(appConfigDescriptor).orDie

  val migrationConfigLive: ULayer[Has[MigrationConfig]] =
    TypesafeConfig.fromDefaultLoader(appConfigDescriptor).narrow(_.migration).orDie
}
