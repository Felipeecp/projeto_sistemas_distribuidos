FROM openjdk:17
VOLUME /app/log
ADD target/server-0.0.1-SNAPSHOT.jar server-0.0.1-SNAPSHOT.jar
EXPOSE 8380
ENTRYPOINT ["java", "-jar", "server-0.0.1-SNAPSHOT.jar"]
