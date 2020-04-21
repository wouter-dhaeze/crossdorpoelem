#!/bin/bash

SCRIPT_PATH=$(which $0)
export SCRIPT_DIR=$(dirname "$SCRIPT_PATH")

cd $SCRIPT_DIR/..

mvn clean package -DskipTests

cp site-main/target/site-main-*.jar deploy/docker/site.jar

docker build -t cdo-site deploy/docker