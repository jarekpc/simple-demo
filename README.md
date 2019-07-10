OpenShift + jenkins konfiguracja:

stworzenie instancji jenkins'a:

 oc new-app jenkins-ephemeral -p NAMESPACE=ci -p JENKINS_IMAGE_STREAM_TAG=jenkins:latest -p MEMORY_LIMIT=2Gi


ustawienie webhook dla repo (na githubie w settings -> klikasz adres swojego jenkinsa  ustawiasz hasło - musi
być ustawiony format "application/json")

import pipeline do opernshift YAML (.simple-service-pipeline.yaml):


Uruchomienie docker compose z keycloak i postrgres

docker-compose -f keycloak-postgres.yml up -d --build

docker-compose up -d --build

remove services:
docker-compose -f keycloak-postgres.yml rm

mysql

docker-compose -f keycloak-mysql.yml up -d --build

Nowa wersja:
\oc.exe cluster up --skip-registry-check=true

oc new-app -e POSTGRESQL_USER=keycloak -e POSTGRESQL_PASSWORD=keycloak -e POSTGRESQL_DATABASE=keycloak-db postgresql

//oc replace --force -f "https://raw.githubusercontent.com/jboss-dockerfiles/keycloak/master/openshift-examples/keycloak-https.json"
keycloak-postgres-template-cluster

.\oc.exe port-forward nazwa_pod &

.\oc.exe import-image jboss/keycloak --confirm   

Tworzenie keycloak + cluster + postgres 
1. Import keycloak-postgres-template-cluster.json do openshift

2. w przypadku braku obrazowu podpiac:
.\oc.exe import-image jboss/keycloak --confirm   

3. przekierowac porty do postgres
.\oc.exe port-forward nazwa_pod 5432 &

