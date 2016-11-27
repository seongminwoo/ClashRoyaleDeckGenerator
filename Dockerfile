FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD build/libs/decksimulator-0.0.2-SNAPSHOT.jar app.jar
# copy UI static files(not included at jar file.)
COPY src/main/webapp/build/ /src/main/webapp/build/
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]