OpenShift + jenkins konfiguracja:

stworzenie instancji jenkins'a:

 oc new-app jenkins-ephemeral \
    -p NAMESPACE=ci \
    -p JENKINS_IMAGE_STREAM_TAG=jenkins:latest \
    -p MEMORY_LIMIT=2Gi


ustawienie webhook dla repo (na githubie w settings -> klikasz adres swojego jenkinsa  ustawiasz hasło - musi
być ustawiony format "application/json")

import pipeline do opernshift YAML (.simple-service-pipeline.yaml):


Uruchomienie docker compose z keycloak i postrgres

docker-compose -f keycloak-postgres.yml up -d --build

docker-compose up -d --build

mysql

docker-compose -f keycloak-mysql.yml up -d --build