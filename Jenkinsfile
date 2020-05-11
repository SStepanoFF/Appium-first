#!/usr/bin/env groovy

pipeline {
    agent any

    options {
        timestamps()
        timeout(time: 120, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    parameters {
        choice(name: 'SERVER', choices: "dev\nmock", description: 'Choose a server where to run API tests.')
        string(name: 'MOBILE_DEVICES', defaultValue: 'All', description: 'Select mobile devices to run. Options: [iPhone8, Samsung_Galaxy_S10]')
        booleanParam(name: 'QTEST_REPORT', defaultValue: 'false', description: 'Please tick if you want to report to QTest')
    }
}