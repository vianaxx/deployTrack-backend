FROM ubuntu:latest
LABEL authors="ercaavi"

ENTRYPOINT ["top", "-b"]

# Dockerfile

# Etapa 1: construir o app com Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: imagem final com Java
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
