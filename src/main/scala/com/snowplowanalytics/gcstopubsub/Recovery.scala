package com.snowplowanalytics.gcstopubsub

sealed trait Recovery extends Product with Serializable
object Recovery {
  case object Legacy extends Recovery
  case object RefrDeviceTstamp extends Recovery
}
