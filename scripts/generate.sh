#!/bin/bash

pushd . > /dev/null

cd "$( cd "$( dirname "$0" )" && pwd )/../"
mkdir -p ../trainbenchmark-models
for s in 1; do
  java -Xmx15G -jar hu.bme.mit.trainbenchmark.generator.rdf/target/hu.bme.mit.trainbenchmark.generator.rdf-1.0.0-SNAPSHOT.jar -scenario XForm -size $s -workspacePath .. || exit 1
done

popd > /dev/null
