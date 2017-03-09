SpringBoot MongoDB Projekt
===================
Ziel dieses Projekt war es, SpringBoot mit MongoDB zu verknüpfen.
Dafür wurde ein einfaches Adressmanagementsystem aufgesetzt.
Für die REST-Beschreibung wurde RAML verwendet.
Für Tests wurde Fongo implementiert.

# Dokumentation

# Voraussetzung
Es wird Docker benötigt.

# Install
mvn package docker:build

# Starten
docker-compose up

# Stoppen 
docker-compose down

# Manueller Test
curl -H "Content-Type: application/json" -X POST -d "{ \"city\": \"MusterStadt\", \"street\": \"Musterstrasse\", \"postcode\": \"0000\", \"firstname\": \"Max\", \"lastname\": \"Mustermann\" }" http://localhost:8080/v1/address

# Plugins
- RAML
- Spring Boot
- MongoDB
- Fongo