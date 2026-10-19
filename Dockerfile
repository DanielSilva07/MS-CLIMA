# Estágio 1: Build da aplicação usando o Maven Wrapper
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Copia os arquivos de configuração do Maven Wrapper e o pom.xml
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Dá permissão de execução ao mvnw
RUN chmod +x mvnw

# Baixa as dependências (cache)
RUN ./mvnw dependency:go-offline -B

# Copia o código fonte e realiza o empacotamento
COPY src /app/src
RUN ./mvnw package -DskipTests

# Estágio 2: Imagem final leve apenas com o Java Runtime
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copia o jar gerado no estágio anterior
COPY --from=build /app/target/ms-clima-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta da aplicação
EXPOSE 9090

# Configurações de JVM otimizadas para containers (economiza e limita o uso de RAM)
ENTRYPOINT ["java", \
            "-XX:+UseContainerSupport", \
            "-XX:MaxRAMPercentage=75.0", \
            "-XX:+UseG1GC", \
            "-jar", "app.jar"]