FROM openjdk:17-jdk-slim
WORKDIR /spring-app
COPY ./target/docker-compose-crud-ex-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "docker-compose-crud-ex-0.0.1-SNAPSHOT.jar"]

# Use a specific OpenJDK version for consistency
# FROM openjdk:17-jdk-slim
#
# # Set working directory
# WORKDIR /spring-app
#
# # Copy the JAR file from the target directory into the container
# COPY ./target/docker-compose-crud-ex-0.0.1-SNAPSHOT.jar app.jar
#
# # Expose port 3000 (same as in docker-compose)
# EXPOSE 3000
#
# # Run the Spring Boot application
# CMD ["java", "-jar", "app.jar"]