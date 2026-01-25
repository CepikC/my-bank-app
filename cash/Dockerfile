FROM amazoncorretto:21-alpine
WORKDIR /app
COPY cash/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]