pipeline {
   agent any
   stages {

      stage('Init') {
         steps {
            sh "rm -rf build/libs/"
            sh "chmod +x gradlew"
         }
      }

      stage ('Build') {
         steps {
            sh "./gradlew clean build publish --stacktrace"

            archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true
         }
      }
   }
}
