#!/bin/zsh

# Variables
RESET_TEXT_COLOUR="\033[0m"
BLUE_TEXT_COLOUR="\033[0;34m"
GREEN_TEXT_COLOUR="\033[0;32m"
RED_TEXT_COLOUR="\033[0;31m"

ENV_FILE=config/.env

SUCCESS_TEXT="${GREEN_TEXT_COLOUR}SUCCESS${RESET_TEXT_COLOUR}"
FAILED_TEXT="${RED_TEXT_COLOUR}FAILED${RESET_TEXT_COLOUR}"

# Functions
function printStatus() {
  if [[ $1 == 0 ]]
  then
    echo $SUCCESS_TEXT
  else
    echo $FAILED_TEXT
  fi
}

function printEndpoint() {
  if [[ $1 == 0 ]]
  then
    echo "http://localhost:8080/api/hello-world"
  else
    echo "N/A"
  fi
}

function info() {
  echo -ne "[${BLUE_TEXT_COLOUR}INFO${RESET_TEXT_COLOUR}] " $1
}

function printServiceStatus() {
  info "--------------------------------------------------------------------------\n"
  info "XPLORE SERVICE\n"
  info "--------------------------------------------------------------------------\n"
  info "Status: "
  printStatus $1
  info "Endpoint: "
  printEndpoint $1
}

function checkCommandStatus() {
  if [[ $1 != 0 ]]
  then
    printServiceStatus 1
    exit 1
  fi
}

info "Starting up application locally via docker ...\n"

# Load environment variables from .env file
info "Loading environment variables from ${ENV_FILE} [1/4]\n"
source $ENV_FILE &> /dev/null
checkCommandStatus $?

# Shutdown container, and remove images
info "Removing existing container and images [2/4]\n"

info "  - Shutting down and removing existing ${APP_NAME} container\n"
docker compose -f $DOCKER_COMPOSE_FILE -p $APP_NAME down &> /dev/null
checkCommandStatus $?

info "  - Removing existing ${PROJECT_NAME}-postgresql:latest image\n"
docker rmi -f "${PROJECT_NAME}-postgresql:latest" &> /dev/null
checkCommandStatus $?

info "  - Removing existing ${APP_NAME}:latest image\n"
docker rmi -f "${APP_NAME}:latest" &> /dev/null
checkCommandStatus $?

# Move .war file from ./target to ./docker/tomcat/target
info "Copying ROOT.war from target/ to docker/tomcat/target/ [3/4]\n"
cp target/ROOT.war docker/tomcat/target/ &> /dev/null
checkCommandStatus $?

# Run docker-compose.yml
info "Starting up ${APP_NAME} container [4/4]\n"
docker compose -f $DOCKER_COMPOSE_FILE --env-file $ENV_FILE -p $APP_NAME up -d &> /dev/null
checkCommandStatus $?

printServiceStatus 0
