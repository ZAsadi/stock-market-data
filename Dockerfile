FROM openjdk:8-jdk-alpine
LABEL author="Zahra Asadi"

WORKDIR /app
ARG JAR_FILE=core/target/*.jar
COPY ${JAR_FILE} main.jar
ADD core/target/classes/application.properties /app/application.properties

ENTRYPOINT ["java" ,"-Djava.security.egd=file:/dev/./urandom --spring.config.location=classpath:file:/app/application-properties","-jar","/app/main.jar"]

EXPOSE 9090
