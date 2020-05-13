#! /bin/bash

set -e

tag=$1

TOKEN=$(curl -s -H "Content-Type: application/json" -X POST -d '{"username": "'${DOCKER_USER}'", "password": "'${DOCKER_PASSWORD}'"}' https://hub.docker.com/v2/users/login/ | jq -r .token)

function docker_tag_exists() {
    EXISTS=$(curl -s -H "Authorization: JWT ${TOKEN}" https://hub.docker.com/v2/repositories/wouterdhaeze/cdo-site/tags/ | jq -r "[.results | .[]? | .name == \"$tag\"] | any")
    test $EXISTS = true
}

if docker_tag_exists ; then
    echo "Found tag '$tag', deleting it..."
    curl -i -X DELETE -H "Authorization: JWT ${TOKEN}" https://hub.docker.com/v2/repositories/wouterdhaeze/cdo-site/tags/$tag/ > /dev/null 2>&1
    echo "Tag '$tag' deleted"
else
    echo "Tag '$tag' does not exist"
fi