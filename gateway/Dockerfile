FROM openjdk:17
VOLUME /tmp
ADD target/gateway-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]