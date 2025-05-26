FROM openjdk:11

WORKDIR /app

COPY target/scala-2.13/hello-world-api-assembly-0.1.jar /app/hello-world-api.jar

EXPOSE 8080

CMD ["java", "-jar", "hello-world-api.jar"]
