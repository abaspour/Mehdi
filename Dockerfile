FROM adoptopenjdk/openjdk11
ARG JAR_FILE=target/Mehdi-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]