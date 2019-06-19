node('maven') {
  stage('Build') {
    git url: "https://github.com/jarekpc/simple-demo.git"
    sh "mvn package"
    stash name:"jar", includes:"target/*.jar"
  }
  stage('Test') {
    parallel(
      "Simple Tests": {
        sh "mvn verify -P simple-demo-tests"
      },
      "Discount Tests": {
        sh "mvn verify -P discount-tests"
      }
    )
  }
  stage('Build Image') {
    unstash name:"jar"
    sh "oc start-build simple-demo --from-file=target/*.jar --follow"
  }
  stage('Deploy') {
    openshiftDeploy depCfg: 'simple-demo'
    openshiftVerifyDeployment depCfg: 'simple-demo', replicaCount: 1, verifyReplicaCount: true
  }
  stage('System Test') {
    sh "curl -s -X POST http://127.0.0.1:8080/api"
  }
}