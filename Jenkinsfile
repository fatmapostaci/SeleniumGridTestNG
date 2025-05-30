pipeline{
    agent any

    stages{

        stage('test'){

            steps{
                script{

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
}