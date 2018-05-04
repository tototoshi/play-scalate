#!/bin/bash
sbt \
  clean \
  ++2.11.12 \
  plugin/publishSigned \
  clean \
  ++2.12.6 \
  plugin/publishSigned \
  sonatypeRelease
