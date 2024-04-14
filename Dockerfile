FROM openjdk:17
EXPOSE 8080
ADD target/Mailing-0.0.1-SNAPSHOT.jar Mailing.jar
ENTRYPOINT ["java", "-jar", "Mailing.jar"]