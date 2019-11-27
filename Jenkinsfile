#!/usr/bin/env groovy
pipeline{
    agent any

    options {disableConcurrentBuilds()}

    stages {
        stage('Permissions') {
            steps {
                sh 'chmod 775 *'
            }
        }

        stage('Cleanup') {
            steps {
                sh './gradlew --no-daemon clean'
            }
        }

        stage('Spot bugs') {
            steps {
                sh './gradlew --no-daemon spotbugsMain'
            }
            post {
                always {
                    step ([
                            pattern        : 'build/reports/findbugs/*.xml',
                            canRunOnFailed : true
                    ])
                }
            }
        }

        stage('Test') {
            steps {
                sh './gradlew --no-daemon check'
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Build') {
            steps {

                sh './gradlew --no-daemon build -x check'

            }
        }
    }
}