FROM openjdk:21
ARG JAR_FILE=build/libs/USER-SERVICE-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} App.jar
ENTRYPOINT ["java", "-jar", "App.jar"]
