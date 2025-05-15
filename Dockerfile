# Etapa 1: Usando uma imagem Maven que já possui Java 21
FROM maven:3.9.9-amazoncorretto-21 AS builder
WORKDIR /app

# Copia o projeto e executa o build
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Usando uma imagem leve do Amazon Corretto 21 para execução
FROM amazoncorretto:21-al2023
WORKDIR /app

# Copia o JAR da etapa de build
COPY --from=builder /app/target/order-api.jar app.jar

# Define variáveis de ambiente (opcional)
ENV JAVA_OPTS=""

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
