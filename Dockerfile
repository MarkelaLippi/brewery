FROM openjdk:11
COPY ./brewery-0.0.1-SNAPSHOT.jar brewery-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","brewery-0.0.1-SNAPSHOT.jar"]













