# Koristi zvaničnu OpenJDK sliku kao bazu
FROM openjdk:17-jdk-slim

# Postavi radni direktorijum unutar kontejnera
WORKDIR /app

# Kopiraj jar fajl u kontejner
COPY target/formservice-0.0.1-SNAPSHOT.jar /app/forms-service.jar

# Izloži port 8081
EXPOSE 8081

# Definiši komandu za pokretanje aplikacije
CMD ["java", "-jar", "forms-service.jar"]
