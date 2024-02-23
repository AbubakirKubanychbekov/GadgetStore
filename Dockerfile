FROM openjdk:17
WORKDIR /usr/src/app
ARG JAR_FILE=target/*.jar
COPY ./target/GadjetStore-v1.jar app.jar
CMD ["java","-jar","app.jar"]
