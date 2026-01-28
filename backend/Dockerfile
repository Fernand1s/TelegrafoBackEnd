# Estágio 1: Build com Java 21 e Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
# Executa o build ignorando os testes para ser mais rápido no deploy
RUN mvn clean package -DskipTests

# Estágio 2: Execução com JRE 21 leve
FROM eclipse-temurin:21-jre-jammy
# Define o diretório de trabalho
WORKDIR /app
# Copia o JAR gerado no estágio anterior
COPY --from=build /target/*.jar app.jar
# Expõe a porta que o Spring Boot usa
EXPOSE 8080
# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]