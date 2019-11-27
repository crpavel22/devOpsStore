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