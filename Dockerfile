# Example using MS Build of OpenJDK image with a different base image
FROM debian:buster-slim
ENV JAVA_HOME /usr/lib/jvm/msopenjdk-17-amd64
ENV PATH "${JAVA_HOME}/bin:${PATH}"
COPY --from=mcr.microsoft.com/openjdk/jdk:17-ubuntu $JAVA_HOME $JAVA_HOME

# Continue with your application deployment
RUN mkdir /opt/app
COPY target/*.jar /opt/app/app.jar
CMD ["java", "-jar", "/opt/app/app.jar"]