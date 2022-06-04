FROM openjdk:18-slim
COPY . /src
WORKDIR /src
RUN ./gradlew bootJar --no-daemon
EXPOSE 8080
RUN mkdir /app && cp /src/build/libs/*.jar /app/alphabank-test-application.jar
ENTRYPOINT ["java", "-jar", "/app/alphabank-test-application.jar"]