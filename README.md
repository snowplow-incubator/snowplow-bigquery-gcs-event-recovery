# GCS-to-PubSub

## Quickstart

Assuming git and [SBT][sbt] installed:

```bash
$ git clone https://github.com/chuwy/gcs-to-pubsub
$ cd gcs-to-pubsub
$ GOOGLE_APPLICATION_CREDENTIALS=$PATH_TO_CREDS sbt
sbt$ run --input=gs://bucket/sampledata/newsample.txt --output=projects/snowplow-project/topics/dataflow-recovery --runner=DataflowRunner --project=snowplow-project
```

[snowplow]: https://github.com/snowplow/snowplow/
[sbt]: https://www.scala-sbt.org/

