FROM openjdk:17
ADD target/med-api-docker.jar med-api-docker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "med-api-docker.jar"]