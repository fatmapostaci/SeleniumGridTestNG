pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                script {
                    try {
                        // Execute Test
                        bat 'mvn test'
                        //sh 'mvn test'
                    } catch (Exception e) {
                        // Find assertion failure make build UNSTABLE
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }
    }
    post {
        unstable {
            mail to: 'yasinanil.67@gmail.com',
                 subject: "Build Unstable",
                 body: "Build is unstable due to test failures."
        }
    }
}
