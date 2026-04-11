# ── Etapa 1: Build ──────────────────────────────────────────
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ── Etapa 2: Runtime ────────────────────────────────────────
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/proyecto-grpc-1.0-SNAPSHOT.jar app.jar

# MODO controla qué main() ejecutar: "servidor" o "cliente"
ENV MODO=servidor

ENTRYPOINT ["sh", "-c", \
  "if [ \"$MODO\" = 'servidor' ]; then \
     java -cp app.jar com.example.gRPC.servidor; \
   else \
     java -cp app.jar com.example.gRPC.cliente; \
   fi"]