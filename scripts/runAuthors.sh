#!/usr/bin/env bash

(cd .. && mvn clean package)

cp -f ../ws_authors/target/ws_authors-1.0.0.war \
  /opt/payara/micro/6.2024.11/deployments/

# Les propriétés définies dans microprofile-config.properties
# peuvent être remplacées par des variables d'environnement :
#
# export DATABASE_HOST=localhost
# export DATABASE_PORT=5432
# export DATABASE_NAME=upsa
# export DATABASE_USERNAME=system
# export DATABASE_PASSWORD=<your_password>

java -jar /opt/payara/micro/6.2024.11/payara-micro-6.2024.11.jar \
  --deploy /opt/payara/micro/6.2024.11/deployments/ws_authors-1.0.0.war \
  --addLibs /opt/libs/postgresql-42.7.4.jar \
  --contextroot / \
  --port 8081

# ----------------------------------------------------------------------------------------------------------------------
# --deploydir ../target
# Permet de spécifier un répertoire contenant plusieurs fichiers WAR à déployer.