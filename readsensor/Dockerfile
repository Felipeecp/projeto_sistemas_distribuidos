FROM openjdk:17
VOLUME /app/log
ADD target/readsensor-0.0.1-SNAPSHOT.jar readsensor-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "readsensor-0.0.1-SNAPSHOT.jar"]
