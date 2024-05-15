# Use an official Maven image as the base image
FROM openjdk:17 AS build
# Set the working directory in the container
WORKDIR /Swapup
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package -DskipTests
# Use an official OpenJDK image as the base image
FROM openjdk:17
# Set the working directory in the container
WORKDIR /Swapup
# Copy the built JAR file from the previous stage to the container
COPY --from=build /Swapup/target/Swapup.jar .
# Set the command to run the application
CMD ["java", "-jar", "Swapup.jar"]