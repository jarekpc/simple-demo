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
                   git url: "https://github.com/jarekpc/simple-demo.git", branch: "master"
                }
           }

           stage('Build'){
                 steps {
                   sh "mvn -B clean install -DskipTests=true -f ${POM_FILE}"
                 }
           }

             stage('Unit Test'){
                 steps {
                   sh "mvn -B test -f ${POM_FILE}"
                 }
               }

              stage('Build Container Image'){
                    steps {
                            sh """
                                ls target/*
                                rm -rf oc-build && mkdir -p oc-build/deployments
                                for t in \$(echo "jar" | tr ";" "\\n"); do
                                      cp -rfv ./target/*.\$t oc-build/deployments/ 2> /dev/null || echo "No \$t files"
                                    done
                                  """
                                  binaryBuild(projectName: env.BUILD, buildConfigName: env.APP_NAME, artifactsDirectoryName: "oc-build")
                                }
                              }

                              stage('Deploy') {
                                steps {
                                  tagImage(sourceImageName: env.APP_NAME, sourceImagePath: env.BUILD, toImagePath: env.DEV)
                                }
                              }

     }

}