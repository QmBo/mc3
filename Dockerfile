FROM openjdk:11-jdk-oracle
WORKDIR mc3
ADD target/mc3.jar app.jar
ENTRYPOINT java -jar app.jar