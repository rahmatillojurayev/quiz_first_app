FROM openjdk:17

WORKDIR /app

COPY target/app.jar /app/app.jar

EXPOSE 80

ENTRYPOINT ["java", "-jar", "/app/app.jar"]