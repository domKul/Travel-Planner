## Etap 1: Budowanie aplikacji
FROM gradle:7.3.3-jdk17 AS build
COPY . .
RUN gradlew build

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar ./demo.jar
EXPOSE 8080
CMD ["java", "-jar", "demo-aws.jar"]
