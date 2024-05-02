FROM openjdk:17-jdk-alpine
COPY target/knowix-backend-0.0.1-SNAPSHOT.jar knowix-backend-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/knowix-backend-0.0.1.jar"]