package controllers

import play.api._
import play.api.mvc._
import com.github.tototoshi.play2.scalate._

object Application extends Controller {

  def index = Action { implicit request =>
    Ok(Scalate.render("index.jade", Map("message" -> "hello")))
  }

}
