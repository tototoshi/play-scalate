#!/bin/bash
sbt \
  clean \
  ++2.11.12 \
  plugin/publishSigned \
  clean \
  ++2.12.8 \
  plugin/publishSigned \
  sonatypeRelease
