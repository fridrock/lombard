FROM openjdk:21-jdk

WORKDIR /app

COPY . /app

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/build/lombard-0.0.1-SNAPSHOT.jar"]