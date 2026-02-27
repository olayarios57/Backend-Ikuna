# ETAPA 1: Compilación (Build)
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (Runtime)
FROM eclipse-temurin:21-jdk
# Copiamos el archivo .jar generado en la etapa anterior
COPY --from=build /target/*.jar app.jar
# Exponemos el puerto 8080 que es el que usa tu aplicación
EXPOSE 8080
# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]