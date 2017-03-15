package com.github.tototoshi.play2.scalate

import org.scalatest.FunSuite
import play.api.Environment

class ScalateTest extends FunSuite {

  test("#render") {
    val environment = Environment.simple()
    val scalate = new Scalate(environment)
    assert(scalate.render("index.jade", Map("message" -> "hello")).toString.trim === "<p>hello</p>")
  }

}
