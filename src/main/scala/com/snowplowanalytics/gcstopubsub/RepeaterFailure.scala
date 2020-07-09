/*
 * Copyright (c) 2019-2020 Snowplow Analytics Ltd. All rights reserved.
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
package com.snowplowanalytics.gcstopubsub

import io.circe.{Json, JsonObject, Decoder, Error}
import io.circe.generic.semiauto.deriveDecoder
import io.circe.parser.{ parse => parseJson }

import cats.syntax.eq._

case class RepeaterFailure(payload: JsonObject, error: JsonObject) {
  def isRefrDeviceTstamp: Boolean =
    error === RepeaterFailure.RefrDeviceTstamp

  def fixRefDeviceTstamp: Json =
    payload(RepeaterFailure.ProblematicField) match {
      case Some(json) =>
        val updated = payload
          .remove(RepeaterFailure.ProblematicField)
          .add(RepeaterFailure.ProperField, json)
        Json.fromJsonObject(updated)
      case None =>
        Json.fromJsonObject(payload)
    }
}

object RepeaterFailure {

  val ProblematicField = "refr_device_tstamp"
  val ProperField = "refr_dvce_tstamp"

  val RefrDeviceTstamp = JsonObject.fromMap(Map(
    "BigQueryError" -> Json.fromFields(List(
      "reason" -> Json.fromString("invalid"),
      "location" -> Json.fromString(ProblematicField),
      "message" -> Json.fromString("no such field.")
    ))
  ))

  implicit val repeaterFailureDecoder: Decoder[RepeaterFailure] =
    deriveDecoder[RepeaterFailure]

  def parse(s: String): Either[Error, RepeaterFailure] =
    parseJson(s).flatMap(_.as[RepeaterFailure])
}
