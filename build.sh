#!/bin/sh
cd $TRAVIS_BUILD_DIR
./gradle assemble
./gradlew check
