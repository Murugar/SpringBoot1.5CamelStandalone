FROM openjdk:8u141-jdk-slim

EXPOSE 9005
RUN mkdir /app/
COPY target/springcamelstandalone-1.0.1.jar /app/
ENTRYPOINT exec java $JAVA_OPTS -Dactivemq.hostname='magic-broker' -jar /app/springcamelstandalone-1.0.1.jar