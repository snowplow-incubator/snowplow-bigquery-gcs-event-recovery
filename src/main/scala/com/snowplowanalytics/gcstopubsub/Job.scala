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

import com.spotify.scio.ScioContext
import com.spotify.scio.coders._
import com.spotify.scio.pubsub._
import com.spotify.scio.values._

import io.circe.parser.decode
import io.circe.generic.semiauto.deriveDecoder

import com.snowplowanalytics.snowplow.badrows._
import com.snowplowanalytics.snowplow.badrows.BadRow._
import com.snowplowanalytics.iglu.core.SelfDescribingData
import com.snowplowanalytics.iglu.core.circe.implicits._

object Job {
  implicit val sdjLoaderParsingErr =
    deriveDecoder[SelfDescribingData[BadRow.LoaderParsingError]]

  def run(sc: ScioContext, input: String, output: String): Unit = {
    sc.textFile(input)
      .map(decode[SelfDescribingData[BadRow.LoaderParsingError]])
      .map(_.toOption)
      .collect { case Some(SelfDescribingData(_, err: BadRow.LoaderParsingError)) =>
        err.payload.event
      }
      .write(PubsubIO.string(output))(PubsubIO.WriteParam())
  }
}
