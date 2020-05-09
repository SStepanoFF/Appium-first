#!/bin/bash
function new_tab() {
  TAB_NAME=$1
  COMMAND=$2

   osascript \
   -e "tell application \"Terminal\" to activate" \
   -e "tell application \"System Events\" to tell process \"Terminal\" to keystroke \"t\" using command down" \
   -e "tell application \"Terminal\" to do script with command \"cd $PWD/gridConfig\" in selected tab of the front window" \
   -e "tell application \"Terminal\" to do script \"printf '\\\e]1;$TAB_NAME\\\a'; $COMMAND\" in front window" \

}

#osascript -i <<EOF
#        tell application "iTerm"
#            tell current window
#                create tab with default profile
#                tell the current session
#                    write text "cd \"$PWD\" $cmd"
#                end tell
#            end tell
#        end tell
#EOF


#For Terminal app
#   osascript \
#   -e "tell application \"Terminal\" to activate" \
#   -e "tell application \"System Events\" to tell process \"Terminal\" to keystroke \"t\" using command down" \
#   -e "tell application \"Terminal\" to do script with command \"cd $PWD/gridConfig\" in selected tab of the front window" \
#   -e "tell application \"Terminal\" to tell selected tab of the front window to set custom title to \"$1\"" \
#   -e "tell application \"Terminal\" to do script with command \"$COMMAND\" in selected tab of the front window" \
#
#}



SELENIUM_SERVER_FILE="selenium-server-standalone-3.141.59.jar"

new_tab "Grid" "java -jar $SELENIUM_SERVER_FILE -role hub -hubConfig hubConfig.json"
new_tab "Node1-iPhone8" "appium --port 4723 --nodeconfig nodeConfigiPhone8.json --session-override"
new_tab "Node2-GalaxyS10" "appium --port 4724 --nodeconfig nodeConfigGalaxyS10.json --session-override"