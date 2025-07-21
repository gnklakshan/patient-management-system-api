#build environment
FROM maven:3.9.9-eclipse-temurin-17 AS build

#Set the working directory inside the container to /app
WORKDIR /app

#Copy only the pom.xml file into the container
COPY pom.xml .

#Download all dependencies without building the ap
RUN mvn dependency:go-offline -B

#Copy the entire source code into the container
COPY src ./src

#Build the application using Maven
RUN mvn clean package

# Production environment --Runtime
FROM openjdk:17-jdk-slim AS runner

WORKDIR /app

# Copy the built JAR file from the builder stage to the current working directory
COPY --from=build /app/target/patient-service-0.0.1-SNAPSHOT.jar ./app.jar

#application port
EXPOSE 4000

# Set the entry point for the container to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

