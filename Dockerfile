FROM openjdk:8

COPY JavaSpringAppTask-0.0.1-SNAPSHOT.jar JavaSpringAppTask-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java", "-jar", "/JavaSpringAppTask-0.0.1-SNAPSHOT.jar" ]