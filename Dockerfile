FROM java:8
FROM maven:alpine

WORKDIR /app

COPY . /app

RUN mvn -v
RUN mvn clean install -DskipTests
EXPOSE 8080
LABEL maintainer='weeskass@gmail.com'
ADD ./target/kotlin-demo-0.0.1-SNAPSHOT.jar kotlin-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","kotlin-demo-0.0.1-SNAPSHOT.jar"]