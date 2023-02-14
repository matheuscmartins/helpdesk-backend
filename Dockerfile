FROM openjdk

WORKDIR /app

COPY target/helpdesk-0.0.1-SNAPSHOT.jar /app/helpdesk.jar

ENTRYPOINT [ "java", "-jar", "helpdesk.jar" ]