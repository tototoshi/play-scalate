lazy val plugin = Project (
  id = "plugin",
  base = file ("plugin")
).settings(
  Seq(
    name := "play-scalate",
    organization := "org.scalatra.scalate",
    version := "0.4.1-SNAPSHOT",
    scalaVersion := "2.12.6",
    crossScalaVersions := Seq("2.11.12", "2.12.6"),
    resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play" % play.core.PlayVersion.current % "provided",
      "org.scalatra.scalate" %% "scalate-core" % "1.8.0" % "provided"
    ),
    scalacOptions ++= Seq("-language:_", "-deprecation")
  ) ++ publishingSettings :_*
)

val playAppName = "playapp"
val playAppVersion = "1.0-SNAPSHOT"

lazy val playapp = Project(
  playAppName,
  file("playapp")
).enablePlugins(PlayScala)
.settings(
  resourceDirectories in Test += baseDirectory.value / "conf",
  crossScalaVersions := Seq("2.12.6", "2.11.12"),
  scalaVersion := "2.12.6",
  version := playAppVersion,
  libraryDependencies ++= Seq(
    guice,
    "org.scalatra.scalate" %% "scalate-core" % "1.8.0",
    "org.scala-lang" % "scala-compiler" % scalaVersion.value,
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
  ),
  unmanagedResourceDirectories in Compile += baseDirectory.value / "app" / "views"
)
.dependsOn(plugin)

val publishingSettings = Seq(
  publishMavenStyle := true,
  publishTo := _publishTo(version.value),
  publishArtifact in Test := false,
  pomExtra := _pomExtra
)

def _publishTo(v: String) = {
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

val _pomExtra =
  <url>http://github.com/scalate/play-scalate</url>
  <licenses>
    <license>
      <name>Apache License, Version 2.0</name>
      <url>http://github.com/scalate/play-scalate/blob/master/LICENSE.txt</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:scalate/play-scalate.git</url>
    <connection>scm:git:git@github.com:scalate/play-scalate.git</connection>
  </scm>
  <developers>
    <developer>
      <id>tototoshi</id>
      <name>Toshiyuki Takahashi</name>
      <url>http://tototoshi.github.com</url>
    </developer>
  </developers>
