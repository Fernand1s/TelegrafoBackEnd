# Estágio 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS build
# Isso cria uma pasta e entra nela dentro do container
WORKDIR /app
# Copia tudo para dentro dessa pasta /app
COPY . .
# Agora o mvn vai rodar dentro de /app, onde o pom.xml estará
RUN mvn clean package -DskipTests

# Estágio 2: Execução
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copia o JAR que foi gerado na pasta target do estágio anterior
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
