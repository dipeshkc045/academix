FROM openjdk:17
ARG JAR_FILE=build/libs/USER-SERVICE-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} App.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=staging", "-jar", "App.jar"]