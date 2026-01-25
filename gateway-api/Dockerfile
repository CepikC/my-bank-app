FROM amazoncorretto:21-alpine
WORKDIR /app
COPY gateway-api/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]