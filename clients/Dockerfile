FROM openjdk:17
VOLUME /tmp
ADD target/clients-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]