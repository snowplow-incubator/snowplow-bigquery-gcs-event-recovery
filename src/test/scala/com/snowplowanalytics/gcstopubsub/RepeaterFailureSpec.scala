package com.snowplowanalytics.gcstopubsub

import org.specs2.mutable.Specification

class RepeaterFailureSpec extends Specification {
  "parse" should {
    "parse valid anonymized (without most Snowplow properties) row" >> {
      val input = """{"payload":{"dvce_created_tstamp":"2020-04-25T03:39:33.942Z","event":"unstruct","event_id":"17cda2fa-2ef7-40e6-8da8-cd21a1dc109b","name_tracker":"co","v_tracker":"js-2.11.0","v_collector":"ssc-0.17.0-googlepubsub","v_etl":"beam-enrich-0.4.0-common-0.38.0","refr_device_tstamp":"2020-04-25T03:38:56.189Z","contexts_com_snowplowanalytics_snowplow_ua_parser_context_1_0_0":[{"device_family":"Generic Smartphone","os_family":"Android","useragent_family":"Facebook","os_major":null,"os_minor":null,"os_patch":null,"os_patch_minor":null,"os_version":"Android","useragent_major":"266","useragent_minor":"0","useragent_patch":"0","useragent_version":"Facebook 266.0.0"}],"event_vendor":"com.snowplowanalytics.snowplow","event_name":"link_click","event_format":"jsonschema","event_version":"1-0-1","event_fingerprint":"14445429e970f385084cdf8b112fff0f"},"error":{"BigQueryError":{"reason":"invalid","location":"refr_device_tstamp","message":"no such field."}}}""".stripMargin
      RepeaterFailure.parse(input) must beRight
    }
  }

  "isRefrDeviceTstamp" should {
    "identify its error" >> {
      val input = """{"payload":{"dvce_created_tstamp":"2020-04-25T03:39:33.942Z","event":"unstruct","event_id":"17cda2fa-2ef7-40e6-8da8-cd21a1dc109b","name_tracker":"co","v_tracker":"js-2.11.0","v_collector":"ssc-0.17.0-googlepubsub","v_etl":"beam-enrich-0.4.0-common-0.38.0","refr_device_tstamp":"2020-04-25T03:38:56.189Z","contexts_com_snowplowanalytics_snowplow_ua_parser_context_1_0_0":[{"device_family":"Generic Smartphone","os_family":"Android","useragent_family":"Facebook","os_major":null,"os_minor":null,"os_patch":null,"os_patch_minor":null,"os_version":"Android","useragent_major":"266","useragent_minor":"0","useragent_patch":"0","useragent_version":"Facebook 266.0.0"}],"event_vendor":"com.snowplowanalytics.snowplow","event_name":"link_click","event_format":"jsonschema","event_version":"1-0-1","event_fingerprint":"14445429e970f385084cdf8b112fff0f"},"error":{"BigQueryError":{"reason":"invalid","location":"refr_device_tstamp","message":"no such field."}}}""".stripMargin
      val failure = RepeaterFailure.parse(input).right.get
      failure.isRefrDeviceTstamp must beTrue
    }
  }
}
