FROM openjdk:21-jdk as build

WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod -R 777 ./mvnw

# Build the application
RUN ./mvnw install -DskipTests

FROM openjdk:21-slim

WORKDIR /app

# Copy the JAR file directly (simpler approach)
COPY --from=build /workspace/app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]