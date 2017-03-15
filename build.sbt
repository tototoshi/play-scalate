scalariformSettings

lazy val plugin = Project (
  id = "plugin",
  base = file ("plugin")
).settings(
  Seq(
    name := "play-scalate",
    organization := "com.github.tototoshi",
    version := "0.3.0",
    scalaVersion := "2.12.1",
    crossScalaVersions := Seq("2.12.1", "2.11.8"),
    resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play" % play.core.PlayVersion.current % "provided",
      "org.scalatra.scalate" %% "scalate-core" % "1.8.0" % "provided",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    ),
    scalacOptions ++= Seq("-language:_", "-deprecation")
  ) ++ scalariformSettings ++ publishingSettings :_*
)

val playAppName = "playapp"
val playAppVersion = "1.0-SNAPSHOT"

lazy val playapp = Project(
  playAppName,
  file("playapp")
).enablePlugins(PlayScala).settings(scalariformSettings:_*)
.settings(
  resourceDirectories in Test += baseDirectory.value / "conf",
  scalaVersion := "2.12.1",
  version := playAppVersion,
  libraryDependencies ++= Seq(
    "org.scalatra.scalate" %% "scalate-core" % "1.8.0",
    "org.scala-lang" % "scala-compiler" % scalaVersion.value,
    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  ),
  unmanagedResourceDirectories in Compile += baseDirectory.value / "app" / "views"
)
.dependsOn(plugin)

val publishingSettings = Seq(
  publishMavenStyle := true,
  publishTo <<= version { (v: String) => _publishTo(v) },
  publishArtifact in Test := false,
  pomExtra := _pomExtra
)

def _publishTo(v: String) = {
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

val _pomExtra =
  <url>http://github.com/tototoshi/play-scalate</url>
  <licenses>
    <license>
      <name>Apache License, Version 2.0</name>
      <url>http://github.com/tototoshi/play-scalate/blob/master/LICENSE.txt</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:tototoshi/play-scalate.git</url>
    <connection>scm:git:git@github.com:tototoshi/play-scalate.git</connection>
  </scm>
  <developers>
    <developer>
      <id>tototoshi</id>
      <name>Toshiyuki Takahashi</name>
      <url>http://tototoshi.github.com</url>
    </developer>
  </developers>
