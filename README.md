##1. Selenium grid
###1.1. Start Selenium grid hub
In Terminal switch to "seleniumGrid" folder

`% java -jar selenium-server-standalone-3.141.59.jar -role hub -hubConfig hubConfig.json`
###1.2. Start appium and registered node to Selenium Grid
`% appium --port 4724 --nodeconfig nodeConfigGalaxyS10.json`
port should be the same as in json config file (in exampl it is nodeConfigGalaxyS10.json)

"launch_grid.sh" responsible for launch Selenium grid and Appium nodes

`% sh src/main/resources/scripts/launch_grid.sh`

##2. Start emulators
###2.1. Start Genymotion emulator
`open -a /Applications/Genymotion.app/Contents/MacOS/player.app --args --vm-name '{name or UDID}'`

##3. How to run with Gradle
`% ./gradlew`

##4. Jenkins
###4.1. Start Jenkins
`brew services start jenkins-lts`
###4.2. Jenkins url
`http://jenkins.sst.com:8080`
admin
admin15`

