#!/usr/bin/env groovy

pipeline {
    agent any

//    poll githab every 1 min to check changes
//    triggers {pollSCM('* * * * *')}

    options {
        timestamps()
        timeout(time: 120, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '7'))
    }

    parameters {
        extendedChoice(name: 'MOBILE_DEVICES', defaultValue: 'iPhone8, Samsung_Galaxy_S10', multiSelectDelimiter: ',', type: 'PT_MULTI_SELECT', value: 'iPhone8, Samsung_Galaxy_S10, Pixel XL ASDK', description: 'Select mobile devices to run. Options: [iPhone8, Samsung_Galaxy_S10]')
        choice(name: 'SERVER', choices: "dev\nmock", description: 'Choose a server where to run API tests.')
        string(name: 'STRING_EXAMPLE', defaultValue: 'All', description: 'Select mobile devices to run. Options: [iPhone8, Samsung_Galaxy_S10]')
        booleanParam(name: 'QTEST_REPORT', defaultValue: 'false', description: 'Please tick if you want to report to QTest')
    }

    stages {
        stage('Start SeleniumGrid and Appium') {
            steps {
                echo 'starting .sh......'
//                for execution need permissions - execute with zsh or first set 'chmod +x' permission and after execute
//                sh 'zsh ./src/main/resources/scripts/jenkins_launch_grid.sh'


                sh 'java -jar ./gridConfig/selenium-server-standalone-3.141.59.jar -role hub -hubConfig ./gridConfig/hubConfig.json > /dev/null 2>&1 &'
                sleep 5
                sh 'appium --port 4723 --nodeconfig ./gridConfig/nodeConfigiPhone8.json --session-override'
                sleep 10
            }
        }

    }
    post {
        always {
            echo "Stop appium server"
            sh "killall -9 node"
        }
    }
}