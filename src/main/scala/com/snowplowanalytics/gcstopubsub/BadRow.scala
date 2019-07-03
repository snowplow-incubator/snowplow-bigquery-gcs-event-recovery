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
package com.snowplowanalytics.gcstopubsub

import cats.syntax.either._
import cats.syntax.show._

import java.util.Base64.getDecoder

import io.circe.Decoder
import io.circe.parser.{ parse => parseJson }
import io.circe.generic.semiauto._


/**
  * Bad row extracted after BigQuery loader
  * @param line original base64-encoded TSV line
  * @param errors list of errors
  */
case class BadRow(line: String, errors: List[String]) {
  def getTsv: String =
    new String(BadRow.Base64Decoder.decode(line))
}

object BadRow {
  private val Base64Decoder = getDecoder

  implicit val originalDecoder: Decoder[BadRow] =
    deriveDecoder[BadRow]

  def parse(line: String): BadRow =
    parseJson(line)
      .flatMap(_.as[BadRow])
      .valueOr(error => throw new RuntimeException(s"Unexpected data in GCS: $line. ${error.show}"))
}

