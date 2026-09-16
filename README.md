# Gestion d'une bibliothèque — Microservices

Projet académique réalisé dans le cadre de ma formation en développement informatique.

Cette application permet de gérer une bibliothèque à travers une architecture basée sur des microservices. Elle prend en charge la gestion des auteurs et des livres, avec un Gateway servant de point d'accès central aux différents services.

Le projet intègre également PostgreSQL pour la persistance des données, Redis pour la mise en cache et Flyway pour la gestion des migrations de la base de données.

## Technologies utilisées

- Java
- Quarkus
- Jakarta EE / MicroProfile
- PostgreSQL
- Redis
- Flyway
- Docker
- Docker Compose
- Apache Maven
- OpenAPI / Swagger

## Architecture

Le projet est composé de plusieurs modules :

- `common` : composants et entités partagés entre les différents modules
- `ws_authors` : microservice responsable de la gestion des auteurs
- `ws_books` : microservice responsable de la gestion des livres
- `gateway` : point d'accès central aux différents microservices
- `scripts` : scripts permettant de lancer certains services avec Payara Micro
- `common/flyway` : scripts de migration de la base de données

L'infrastructure nécessaire au projet est définie dans :

```text
docker-compose-bookshop.yml
```

## Fonctionnalités

- Gestion des auteurs
- Gestion des livres
- Architecture basée sur plusieurs microservices
- Communication entre les différents services
- Accès centralisé via un Gateway
- Persistance des données avec PostgreSQL
- Mise en cache des données avec Redis
- Gestion des migrations avec Flyway
- Health checks des différents services
- Documentation des API avec OpenAPI et Swagger UI
- Déploiement de l'environnement avec Docker Compose

## Prérequis

Pour exécuter le projet avec Docker Compose, il est nécessaire d'avoir installé :

- Docker
- Docker Compose

Pour le développement local, il est également recommandé d'avoir :

- Java
- Apache Maven

## Configuration

Le projet utilise des variables d'environnement pour les informations de connexion à PostgreSQL.

Créer un fichier `.env` à la racine du projet :

```env
POSTGRES_DB=upsa
POSTGRES_USER=system
POSTGRES_PASSWORD=your_password
```

> Le fichier `.env` contient des informations locales et ne doit pas être ajouté au dépôt Git.

## Exécution avec Docker Compose

Depuis la racine du projet :

```bash
docker compose -f docker-compose-bookshop.yml up --build
```

Docker Compose démarre les différents composants nécessaires :

- PostgreSQL
- Redis
- Flyway
- `ws_authors`
- `ws_books`
- `gateway`

Pour arrêter les services :

```bash
docker compose -f docker-compose-bookshop.yml down
```

## Ports

| Service | Port |
|---|---:|
| Gateway | 8888 |
| Authors API | 8081 |
| Books API | 8082 |
| PostgreSQL | 5432 |
| Redis | 6379 |

## Documentation de l'API

Le Gateway expose la documentation OpenAPI et Swagger UI.

Une fois l'application démarrée :

```text
http://localhost:8888/swagger-ui
```

OpenAPI :

```text
http://localhost:8888/openapi
```

## Base de données

PostgreSQL est utilisé pour la persistance des données.

Les migrations sont gérées avec Flyway afin de créer et initialiser la structure de la base de données lors du démarrage de l'environnement.

Les scripts de migration sont disponibles dans le module `common`.

## Cache Redis

Redis est utilisé par le Gateway pour mettre en cache certaines données, notamment les auteurs et les livres.

Cette mise en cache permet de limiter les appels répétés aux microservices lorsque les données sont déjà disponibles.

## Health checks

Les différents services exposent des endpoints permettant de vérifier leur état.

Docker Compose utilise ces health checks afin de contrôler l'ordre de démarrage et de vérifier que les services nécessaires sont disponibles.

## Exécution locale

Les services peuvent également être lancés individuellement en dehors de Docker.

Dans ce cas, les paramètres de connexion à la base de données peuvent être fournis à travers des variables d'environnement :

```bash
export DATABASE_HOST=localhost
export DATABASE_PORT=5432
export DATABASE_NAME=upsa
export DATABASE_USERNAME=system
export DATABASE_PASSWORD=your_password
```

Des scripts d'exécution sont également disponibles dans le répertoire :

```text
scripts/
```

## Objectifs du projet

Ce projet m'a permis de mettre en pratique :

- Le développement d'applications Java
- La conception d'une architecture microservices
- La création et la consommation de services REST
- La mise en place d'un Gateway
- La gestion d'une base de données PostgreSQL
- L'utilisation de Flyway pour les migrations
- L'utilisation de Redis comme système de cache
- La configuration d'applications avec des variables d'environnement
- L'utilisation de Docker et Docker Compose
- La mise en place de health checks
- La documentation d'API avec OpenAPI et Swagger
- Le développement d'un projet Maven multi-module
