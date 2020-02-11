scalacOptions ++= Seq("-deprecation", "-unchecked", "-language:_")

resolvers += Resolver.typesafeRepo("releases")

addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.3")
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.0")
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.2")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "2.4")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.1")
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.4.0")

