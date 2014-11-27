#!/bin/bash

pushd . > /dev/null

cd "$( cd "$( dirname "$0" )" && pwd )/../"

mvn clean install -f ../trainbenchmark-core/pom.xml -P pojo || exit 1

popd > /dev/null
