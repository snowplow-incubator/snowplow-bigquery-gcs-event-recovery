/*
 * Copyright (c) 2019 Snowplow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
import sbt._
import Keys._
import com.typesafe.sbt.packager.Keys.{packageName, maintainer, daemonUser ,daemonUserUid, defaultLinuxInstallLocation}
import com.typesafe.sbt.packager.docker.DockerPlugin.autoImport._
import sbtbuildinfo._
import sbtbuildinfo.BuildInfoKeys._


object BuildSettings {
  lazy val commonSettings = Seq(
    organization          := "com.snowplowanalytics",
    version               := "0.1.0",
    scalaVersion          := "2.13.11",
    scalacOptions         ++= Seq("-target:jvm-1.8",
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Ymacro-annotations"),
    javacOptions          ++= Seq("-source", "1.8", "-target", "1.8"),
    Global / cancelable   := true,
    resolvers += "Confluent" at "https://packages.confluent.io/maven/",

    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % Dependencies.V.betterMonadicFor),
    addCompilerPlugin("org.typelevel" %% "kind-projector" % Dependencies.V.kindProjector),

    buildInfoKeys := Seq[BuildInfoKey](organization, name, version, description,
      BuildInfoKey.action("userAgent") { s"${name.value}/${version.value}" }
    )
  )

  lazy val dockerSettings = Seq(
    packageName in Docker := "snowplow-bigquery-gcs-event-recovery",
    maintainer in Docker := "Snowplow Analytics Ltd. <support@snowplowanalytics.com>",
    dockerBaseImage := "snowplow/base-debian:0.1.0",
    daemonUser in Docker := "snowplow",
    daemonUserUid in Docker := None,
    defaultLinuxInstallLocation in Docker := "/home/snowplow",
    dockerUpdateLatest := true
  )
}
