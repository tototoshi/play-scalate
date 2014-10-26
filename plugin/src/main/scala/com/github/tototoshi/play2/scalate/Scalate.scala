package com.github.tototoshi.play2.scalate

import play.api.Play
import play.api.Play.current
import play.twirl.api.Html

object Scalate {

  import java.io.{ InputStreamReader, BufferedReader }
  import org.fusesource.scalate._
  import org.fusesource.scalate.util._
  import org.fusesource.scalate.layout.DefaultLayoutStrategy

  class ClassPathResourceLoader extends ResourceLoader {
    private def using[A, R <: { def close() }](r: R)(f: R => A): A =
      try { f(r) } finally { r.close() }

    override def resource(uri: String): Option[Resource] = {
      current.resourceAsStream(uri).map { inputStream =>
        using(inputStream) { in =>
          using(io.Source.fromInputStream(in)) { src =>
            StringResource(uri, src.getLines.mkString("\n"))
          }
        }
      }
    }
  }

  val engine = {
    val e = new TemplateEngine
    e.resourceLoader = new ClassPathResourceLoader()
    val availableTemplateTypes = Seq("jade", "scaml", "mustache", "ssp")
    val defaultTemplates = availableTemplateTypes.map("layouts/default." + _)
    e.layoutStrategy = new DefaultLayoutStrategy(e, defaultTemplates: _*)
    e.classLoader = current.classloader
    e.allowReload = Play.isDev
    e.allowCaching = Play.isProd
    e
  }

  def render(template: String, params: Map[String, Any] = Map.empty): Html = {
    Html(engine.layout(template, params))
  }

}
