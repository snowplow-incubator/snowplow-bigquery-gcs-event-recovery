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

object Dependencies {
  object V {
    // Scala
    val circe              = "0.13.0"
    val scio               = "0.9.2"
    // Java
    val beam               = "2.22.0"
    val googleCloud        = "1.85.1"
    val slf4j              = "1.7.25"
    // Scala (test only)
    val specs2             = "4.3.5"
    val scalaCheck         = "1.14.0"
    // Build
    val scalaMacrosVersion = "2.1.0"
    val betterMonadicFor   = "0.2.4"
    val kindProjector      = "0.9.7"
  }

  val bigQuery           = "com.google.cloud"      %  "google-cloud-bigquery"        % V.googleCloud
  val pubsub             = "com.google.cloud"      %  "google-cloud-pubsub"          % V.googleCloud

  // Scala
  val circe              = "io.circe"              %% "circe-core"                   % V.circe
  val circeGeneric       = "io.circe"              %% "circe-generic"                % V.circe
  val circeParser        = "io.circe"              %% "circe-parser"                 % V.circe
  val scioCore           = "com.spotify"           %% "scio-core"                    % V.scio

  // Java
  val dataflowRunner     = "org.apache.beam"       % "beam-runners-google-cloud-dataflow-java" % V.beam
  val directRunner       = "org.apache.beam"       % "beam-runners-direct-java"                % V.beam
  val slf4j              = "org.slf4j"             % "slf4j-simple"                            % V.slf4j

  // Scala (test only)
  val scalaCheck         = "org.scalacheck"        %% "scalacheck"                   % V.scalaCheck     % "test"
  val specs2             = "org.specs2"            %% "specs2-core"                  % V.specs2         % "test"
  val scioTest           = "com.spotify"           %% "scio-test"                    % V.scio           % "test"
}
