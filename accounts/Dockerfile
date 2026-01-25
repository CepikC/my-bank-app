FROM amazoncorretto:21-alpine
WORKDIR /app
COPY accounts/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]