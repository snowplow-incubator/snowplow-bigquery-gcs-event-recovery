# Snowplow BigQuery GCS Event Recovery

Snowplow BigQuery GCS Event Recovery reads bad rows from [GCS](https://cloud.google.com/storage/) and writes it to given [PubSub](https://cloud.google.com/pubsub/) topic.

## Quickstart

Snowplow BigQuery GCS Event Recovery is hosted on Docker Hub : [snowplow/snowplow-bigquery-gcs-event-recovery](https://cloud.docker.com/u/snowplow/repository/docker/snowplow/snowplow-bigquery-gcs-event-recovery/general).

```bash
# Put GOOGLE_APPLICATION_CREDENTIALS json file to DOCKER_CONFIG_FOLDER
$ export DOCKER_CONFIG_FOLDER=<path-to-docker-config-folder>
$ export GOOGLE_APPLICATION_CREDENTIALS = $DOCKER_CONFIG_FOLDER/<creds-json-file>
$ docker run \
  -v $DOCKER_CONFIG_FOLDER:/snowplow/config \
  -e GOOGLE_APPLICATION_CREDENTIALS=/snowplow/config/$GOOGLE_APPLICATION_CREDENTIALS \
  snowplow/snowplow-bigquery-gcs-event-recovery:0.2.0 \
  --input=gs://bucket/sampledata/newsample.txt \
  --output=projects/snowplow-project/topics/dataflow-recovery \
  --runner=DataflowRunner \
  --project=snowplow-project \
  --recovery=legacy     # optional parameter that also can be refr-device-tstamp
```

## Copyright and license

Snowplow BigQuery GCS Event Recovery is copyright 2019 Snowplow Analytics Ltd.

Licensed under the **[Apache License, Version 2.0][license]** (the "License");
you may not use this software except in compliance with the License.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[snowplow]: https://github.com/snowplow/snowplow/
[sbt]: https://www.scala-sbt.org/
[license]: http://www.apache.org/licenses/LICENSE-2.0
