# Use an official Maven image as the base image
FROM eclipse-temurin:17-jdk AS build
# Set the working directory in the container
WORKDIR /Project
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package -DskipTests
# Use an official OpenJDK image as the base image
FROM eclipse-temurin:17-jdk
# Set the working directory in the container
WORKDIR /Project
# Copy the built JAR file from the previous stage to the container
COPY --from=build /Project/target/Project-0.0.1-SNAPSHOT.jar .
# Set the command to run the application
CMD ["java", "-jar", "Project-0.0.1-SNAPSHOT.jar.jar"]