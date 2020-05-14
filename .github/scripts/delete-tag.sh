#! /bin/bash

set -e

echo "Cleaning all Docker images tags for which the corresponding branch does not exist"

TOKEN=$(curl -s -H "Content-Type: application/json" -X POST -d '{"username": "'${DOCKER_USER}'", "password": "'${DOCKER_PASSWORD}'"}' https://hub.docker.com/v2/users/login/ | jq -r .token)
TAGS=$(curl -s -H "Authorization: JWT ${TOKEN}" https://hub.docker.com/v2/repositories/wouterdhaeze/cdo-site/tags/ | jq -r ".results | .[]? | .name")
BRANCHES=$(curl -s -u $GIT_USER:$GIT_PASSWORD https://api.github.com/repos/wouter-dhaeze/crossdorpoelem/branches | jq -r '.[]? |.name')

function docker_tag_exists() {
    EXISTS=$(curl -s -H "Authorization: JWT ${TOKEN}" https://hub.docker.com/v2/repositories/wouterdhaeze/cdo-site/tags/ | jq -r "[.results | .[]? | .name == \"$tag\"] | any")
    test $EXISTS = true
}

function branch_exists() {
    EXISTS=false
    for BRANCH in ${BRANCHES}
    do
      if [ "$BRANCH" = "$1" ]; then
        EXISTS=true
      fi
    done

    test $EXISTS = true
}

for TAG in $TAGS
do
  if branch_exists "$TAG" ; then
    echo "Image '$TAG' not deleted: branch still exists"
  else
    if [[ "$TAG" =~ ^cdo-* ]]; then
      echo "Branch '$TAG' does not exist, deleting docker image"
      curl -i -X DELETE -H "Authorization: JWT ${TOKEN}" https://hub.docker.com/v2/repositories/wouterdhaeze/cdo-site/tags/$TAG/ > /dev/null 2>&1
      echo "Image '$TAG' deleted"
    else
      echo "Image '$TAG' not deleted: it does not start with the prefix 'cdo-'"
    fi
  fi
done
