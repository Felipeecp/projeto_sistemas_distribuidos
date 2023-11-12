FROM maven:3.9-amazoncorretto-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar
ARG EUREKA_SERVER=eureka
ARG HOST_MQ=localhost
ARG VIRTUAL_HOST=localhost
ARG PORT_MQ=5672
ARG USERNAME_MQ=guest
ARG PASSWORD=guest
ENTRYPOINT java -jar -Dspring.profiles.active=hti app.jar