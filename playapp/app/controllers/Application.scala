package controllers

import javax.inject.{ Inject, Singleton }

import com.github.tototoshi.play2.scalate._
import play.api.mvc._

@Singleton
class Application @Inject() (scalate: Scalate) extends Controller {

  def index = Action { implicit request =>
    Ok(scalate.render("index.jade", Map("message" -> "hello")))
  }

}
