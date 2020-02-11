package com.github.tototoshi.play2.scalate

import javax.inject.{ Inject, Singleton }

import play.api.{ Environment, Mode }
import play.twirl.api.Html

import scala.io.Source

@Singleton
class Scalate @Inject() (environment: Environment) {

  import org.fusesource.scalate._
  import org.fusesource.scalate.layout.DefaultLayoutStrategy
  import org.fusesource.scalate.util._

  class ClassPathResourceLoader extends ResourceLoader {
    private def using[A, R <: { def close(): Unit }](r: R)(f: R => A): A =
      try { f(r) } finally { r.close() }

    override def resource(uri: String): Option[Resource] = {
      environment.resourceAsStream(uri).map { inputStream =>
        using(inputStream) { in =>
          using(Source.fromInputStream(in)) { src =>
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
    e.classLoader = environment.classLoader
    e.allowReload = environment.mode == Mode.Dev
    e.allowCaching = environment.mode == Mode.Prod
    e
  }

  def render(template: String, params: Map[String, Any] = Map.empty): Html = try {
    Html(engine.layout(template, params))
  } catch {
    case e: InvalidSyntaxException =>
      throw new ScalateCompilationException(
        e.brief,
        engine.resourceLoader.load(e.template),
        e.pos.line,
        e.pos.column,
        e.brief,
        e)
  }

}
