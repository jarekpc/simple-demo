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
           stage('Git Checkout') {
                steps {
                  // Turn off Git's SSL cert check, uncomment if needed
                  // sh 'git config --global http.sslVerify false'
                   git url: "https://github.com/jarekpc/simple-demo.git", branch: "master"
                }
           }

           stage('Build'){
                 steps {
                   sh "mvn -B clean install -DskipTests=true -f ${POM_FILE}"
                 }
           }
     }

}