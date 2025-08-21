# SkillCheck
SkillCheck est une application web de gestion et de passation de tests de type QCM. Elle permet de créer, organiser et évaluer des tests, de gérer les questions et réponses, et de suivre les résultats des utilisateurs. 

L’application est développée avec Spring Boot pour le backend et utilise PostgreSQL comme base de données. Celle ci est fournie sous la forme d'un containeur Docker prêt à l'emploi.

Un compte admin est présent en base, il est necessaire pour la configuration initial.

- pseudo: admin

- password: 123456

# Installation

## Configuration des variables d’environnement pour Docker
Copier le fichier env.example en .env et renseigner les variables pour la base PostgreSQL :

```yaml
POSTGRES_USER=username
POSTGRES_PASSWORD=password
POSTGRES_DB=db_name
```

## Configuration des propriétés de l’application
Copier application.properties en application-local.properties et adapter les variables pour l’API :

```yaml
spring.datasource.url=jdbc:postgresql://localhost:5432/db_name
spring.datasource.username=username
spring.datasource.password=password
```

## Changer le port Docker si nécessaire
Par défaut, PostgreSQL expose le port 5432. Si ce port est déjà utilisé sur votre machine, modifier la section ports du docker-compose.yml :

```yaml
ports:
  - "5433:5432"
```

## Démarrer l’application avec Docker Compose

```bash
docker compose up -d
```

Cela va lancer le service PostgreSQL avec les variables définies dans le .env

## Vérifier la connexion à la base

```bash
docker exec -it <nom_du_conteneur_db> psql -U <POSTGRES_USER> -d <POSTGRES_DB>
```

# Utilisation

Une fois le containeur Docker monté, il suffit de lancer le fichier SkillCheckApplication afin de démarrer l'application.

Cette Api est faite pour fonctionner avec une IHM précise, qu'il faut aussi cloner:
- ssh: git@github.com:LarekMehdi/FO-skill-check.git
- https: https://github.com/LarekMehdi/FO-skill-check.git

