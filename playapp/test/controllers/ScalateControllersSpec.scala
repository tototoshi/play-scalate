package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

class ScalateControllersSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "JadeController GET" should {
    "render the index page using jade template" in {
      val request = FakeRequest(GET, "/jade")
      val home = route(app, request).get

      status(home) mustBe OK
      contentAsString(home) must include("hello, jade")
    }
  }

  "MustacheController GET" should {
    "render the index page using mustache template" in {
      val request = FakeRequest(GET, "/mustache")
      val home = route(app, request).get

      status(home) mustBe OK
      contentAsString(home) must include("hello, mustache")
    }
  }
}