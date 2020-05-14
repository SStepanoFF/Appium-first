#!/usr/bin/env groovy

pipeline {
    agent any

    options {
        timestamps()
        timeout(time: 120, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '7'))
    }

    parameters {
        extendedChoice(name: 'MOBILE_DEVICES', defaultValue:'iPhone8, Samsung_Galaxy_S10', multiSelectDelimiter:',', type:'PT_MULTI_SELECT', value:'iPhone8, Samsung_Galaxy_S10, Pixel XL ASDK', description:'Select mobile devices to run. Options: [iPhone8, Samsung_Galaxy_S10]')
        choice(name: 'SERVER', choices: "dev\nmock", description: 'Choose a server where to run API tests.')
        string(name: 'STRING_EXAMPLE', defaultValue: 'All', description: 'Select mobile devices to run. Options: [iPhone8, Samsung_Galaxy_S10]')
        booleanParam(name: 'QTEST_REPORT', defaultValue: 'false', description: 'Please tick if you want to report to QTest')
    }

    stages{
        stage('Start SeleniumGrid and Appium') {
            steps {
                echo 'start .sh'
//                for execution need permissions - execute with zsh or first det 'chmod +x' permission and after execute
                sh 'zsh ./src/main/resources/scripts/launch_grid.sh'
//                sh 'zsh ./src/main/resources/scripts/test.sh'
//                sh 'chmod +x ./src/main/resources/scripts/test.sh'
//                sh './src/main/resources/scripts/test.sh'
            }
        }
    }
}