FROM openjdk:17
VOLUME /app/log
ADD target/sensorsave-0.0.1-SNAPSHOT.jar sensorsave-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "sensorsave-0.0.1-SNAPSHOT.jar"]
