FROM amazoncorretto:21-alpine
WORKDIR /app
COPY transfer/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]