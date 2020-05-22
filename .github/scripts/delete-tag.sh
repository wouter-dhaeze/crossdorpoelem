#! /bin/bash

set -e

TAG=$1

TOKEN=$(curl -s -H "Content-Type: application/json" -X POST -d '{"username": "'${DOCKER_USER}'", "password": "'${DOCKER_PASSWORD}'"}' https://hub.docker.com/v2/users/login/ | jq -r .token)

function docker_tag_exists() {
    EXISTS=$(curl -s -H "Authorization: JWT ${TOKEN}" https://hub.docker.com/v2/repositories/wouterdhaeze/cdo-site/tags/ | jq -r "[.results | .[]? | .name == \"$TAG\"] | any")
    test $EXISTS = true
}

if [[ "$TAG" =~ ^cdo-* ]]; then
  if docker_tag_exists "$TAG" ; then
    echo "Deleting docker image '$TAG'"
    curl -i -X DELETE -H "Authorization: JWT ${TOKEN}" https://hub.docker.com/v2/repositories/wouterdhaeze/cdo-site/tags/$TAG/ > /dev/null 2>&1
    echo "Image '$TAG' deleted"
  else
    echo "Image '$TAG' not deleted: no tag found"
  fi
else
  echo "Image '$TAG' not deleted: it does not start with the prefix 'cdo-'"
fi
