# Use uma imagem base com Java 21
FROM eclipse-temurin:21-jdk

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR gerado para dentro do contêiner
COPY target/comandaRestaurante-0.0.1-SNAPSHOT.jar app.jar

# Define o comando de entrada do contêiner
ENTRYPOINT ["java", "-jar", "app.jar"]