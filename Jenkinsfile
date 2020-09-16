pipeline {
    agent any

    environment {
        JAVA_HOME="/usr/lib/jvm/java-8-openjdk-amd64/jre"
        PATH="${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Build') {
            steps {
                checkout scm
                sh '''
                git checkout ${BRANCH_NAME}
                ./mvnw clean test
                '''
            }
        }
    }
}
