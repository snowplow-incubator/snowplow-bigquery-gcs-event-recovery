lazy val root = project.in(file("."))
  .settings(Seq(
    name := "snowplow-bigquery-gcs-event-recovery",
    description := "Job to sink data from GCS to PubSub",
    buildInfoPackage := "com.snowplowanalytics.gcstopubsub.generated"
  ))
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(DockerPlugin)
  .settings(BuildSettings.dockerSettings)
  .settings(
    BuildSettings.commonSettings,
    BuildSettings.macroSettings,
    libraryDependencies ++= Seq(
      Dependencies.circe,
      Dependencies.circeGeneric,
      Dependencies.circeJavaTime,
      Dependencies.circeParser,

      Dependencies.scioCore,

      Dependencies.slf4j,
      Dependencies.directRunner,
      Dependencies.dataflowRunner,

      Dependencies.specs2,
      Dependencies.scalaCheck
    )
  )
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(BuildInfoPlugin)
