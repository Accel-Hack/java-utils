#!/bin/bash
# move to project root
cd "$(dirname -- "$0")/../" || exit

# run maven
mvn clean
mvn deploy -e
