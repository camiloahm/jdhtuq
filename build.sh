#!/bin/sh
cd $TRAVIS_BUILD_DIR/jdhtuq
./gradle assemble
./gradlew check
