FROM maven:3.9.9-eclipse-temurin

ARG app-dir=/myapp

WORKDIR ${app-dir}

COPY pom.xml .
COPY mvnw .
COPY src src
COPY .mvn .mvn

RUN mvn package -Dmaven.test.skip=true

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar", "target/workshop3-0.0.1-SNAPSHOT.jar"]
