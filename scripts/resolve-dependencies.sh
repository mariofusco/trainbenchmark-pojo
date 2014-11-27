#!/bin/bash

pushd . > /dev/null

cd "$( cd "$( dirname "$0" )" && pwd )/../.."
if [ ! -d trainbenchmark-core ]; then
  git clone https://github.com/FTSRG/trainbenchmark-core.git
fi
trainbenchmark-core/scripts/build.sh || exit 1

popd > /dev/null
