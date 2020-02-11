#!/bin/bash
sbt \
  clean \
  ++2.12.10 \
  plugin/publishSigned \
  clean \
  ++2.13.1 \
  plugin/publishSigned \
  sonatypeRelease
