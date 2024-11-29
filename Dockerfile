FROM openjdk:21-jdk-slim

ARG JAR_FILE=target/orientanet-api-0.0.1.jar

COPY ${JAR_FILE} orientanet-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "orientanet-api.jar"]
