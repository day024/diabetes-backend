FROM openjdk:17-jdk

COPY server/build/libs/*SNAPSHOT.jar onetool-server.jar

RUN git submodule update --init --recursive

ENTRYPOINT ["java", "-jar", "/onetool-server.jar"]
ENTRYPOINT ["/bin/bash", "-c", "sleep 500"]