# Use OpenJDK 21 base image
FROM openjdk:21

# Define the path to the JAR file
ARG JAR_FILE=build/libs/USER-SERVICE-0.0.1-SNAPSHOT.jar

# Copy the JAR file into the container
COPY ${JAR_FILE} app.jar

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
