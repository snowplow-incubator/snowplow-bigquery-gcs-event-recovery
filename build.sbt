lazy val root = project.in(file("."))
  .settings(Seq(
    name := "gcs-to-pubsub",
    description := "Job to sink data from GCS to PubSub",
    buildInfoPackage := "com.snowplowanalytics.gcstopubsub.generated"
  ))
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
