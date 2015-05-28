package com.github.tototoshi.play2.scalate

import play.api.PlayException

final class ScalateCompilationException(
  override val sourceName: String,
  override val input: String,
  override val line: Integer,
  override val position: Integer,
  message: String,
  cause: Throwable) extends PlayException.ExceptionSource("scalate compilation exception", message, cause)

