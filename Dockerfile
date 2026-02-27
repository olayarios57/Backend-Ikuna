# ETAPA 1: Compilación
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución
FROM eclipse-temurin:21-jdk
COPY --from=build /target/*.jar app.jar
# Exponemos el puerto 8080 (interno)
EXPOSE 8080
# Comando para iniciar la aplicación usando el puerto que asigne Render
ENTRYPOINT ["java", "-Xmx512m", "-Dserver.port=${PORT:8080}", "-jar", "app.jar"]