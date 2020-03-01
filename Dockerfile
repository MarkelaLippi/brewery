FROM openjdk:11-jre-alpine
MAINTAINER Markelov Sergey <RoadToJob2019@gmail.com>
COPY target/brewery-0.0.1-SNAPSHOT.jar /opt/brewery.jar
CMD exec java -jar /opt/brewery.jar



