
library identifier: "pipeline-library@master",
retriever: modernSCM(
  [
    $class: "GitSCMSource",
    remote: "https://github.com/redhat-cop/pipeline-library.git"
  ]
)

openshift.withCluster() {
  env.NAMESPACE = openshift.project()
  env.POM_FILE = env.BUILD_CONTEXT_DIR ? "${env.BUILD_CONTEXT_DIR}/pom.xml" : "pom.xml"
  env.APP_NAME = "${JOB_NAME}".replaceAll(/-build.*/, '')
  echo "Starting Pipeline for ${APP_NAME}..."
  env.BUILD = "${env.NAMESPACE}"
  env.DEV = "${APP_NAME}-dev"
  env.STAGE = "${APP_NAME}-stage"
  env.PROD = "${APP_NAME}-prod"
}

pipeline {
   agent {
      label 'maven'
   }
      stages {
          stage('Build') {
              git url: "https://github.com/jarekpc/simple-demo.git"
              sh "mvn package"
              stash name:"jar", includes:"target/*.jar"
            }
            stage('Test') {
                  steps {
                        sh "mvn -B test -f ${POM_FILE}"
                      }
            }
            stage('Build Image') {
              unstash name:"jar"
              sh "oc start-build simple-demo --from-file=target/*.jar --follow"
            }
            stage('Deploy') {
              openshiftDeploy depCfg: 'simple-demo'
              openshiftVerifyDeployment depCfg: 'simple-demo', replicaCount: 1, verifyReplicaCount: true
            }
      }
}