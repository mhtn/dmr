pipeline {
    agent any

    environment {
        JAVA_HOME="/opt/java/jdk-12.0.2"
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
