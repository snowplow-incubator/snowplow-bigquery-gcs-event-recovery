# Snowplow BigQuery GCS Event Recovery

## Quickstart

Assuming git and [SBT][sbt] installed:

```bash
$ git clone https://github.com/snowplow-incubator/snowplow-bigquery-gcs-event-recovery
$ cd snowplow-bigquery-gcs-event-recovery
$ GOOGLE_APPLICATION_CREDENTIALS=$PATH_TO_CREDS sbt
sbt$ run --input=gs://bucket/sampledata/newsample.txt --output=projects/snowplow-project/topics/dataflow-recovery --runner=DataflowRunner --project=snowplow-project
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

