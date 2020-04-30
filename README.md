##1. Selenium grid
###1.1 Start Selenium grid hub
In Terminal switch to "seleniumGrid" folder

`% java -jar selenium-server-standalone-3.141.59.jar -role hub -hubConfig hubConfig.json`
###1.2 Start appium and registered node to Selenium Grid
`% appium --port 4724 --nodeconfig nodeConfigGalaxyS10.json`
port should be the same as in json config file (in exampl it is nodeConfigGalaxyS10.json)

##2. How to run with Gradle
`% ./gradlew`

