FROM openjdk-21

WORKDIR /app

COPY . /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/build/lombard-0.0.1-SNAPSHOT.jar"]