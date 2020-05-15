#!/usr/bin/env zsh

function new_tab() {
  COMMAND_NAME=$1
  COMMAND=$2

echo 'HELLO......!!!'
echo 'Current folder ' +$PWD
echo "Starting ${COMMAND_NAME} .........."

#java -jar ./gridConfig/selenium-server-standalone-3.141.59.jar -role hub -hubConfig ./gridConfig/hubConfig.json > /dev/null 2>&1 &


#  > /dev/null 2>&1 &  - execute in background mode
echo "Executing comand: $COMMAND"
#$COMMAND > /dev/null 2>&1 &
ls
}

SELENIUM_SERVER_FILE="selenium-server-standalone-3.141.59.jar"
CONFIG_PATH="./gridConfig"

new_tab "Selenium Grid" "java -jar $CONFIG_PATH/$SELENIUM_SERVER_FILE -role hub -hubConfig $CONFIG_PATH/hubConfig.json"
#new_tab "Node1-iPhone8" "appium --port 4723 --nodeconfig $CONFIG_PATH/nodeConfigiPhone8.json --session-override"
#new_tab "Node2-GalaxyS10" "appium --port 4724 --nodeconfig $CONFIG_PATH/nodeConfigGalaxyS10.json --session-override"