# play-scalate

## Install

```scala
libraryDependencies ++= Seq(
  "com.github.tototoshi" %% "play-scalate" % "0.1.0-SNAPSHOT",
  "org.scalatra.scalate" %% "scalate-core" % "1.7.0",
  "org.scala-lang" % "scala-compiler" % scalaVersion.value
)

unmanagedResourceDirectories in Compile += baseDirectory.value / "app" / "views",

unmanagedClasspath in Runtime += baseDirectory.value / "app" / "views"
```

## Usage

```scala
package controllers

import play.api._
import play.api.mvc._
import com.github.tototoshi.play2.scalate._

object Application extends Controller {

  def index = Action { implicit request =>
    Ok(Scalate.render("index.jade", Map("message" -> "hello")))
  }

}
```
