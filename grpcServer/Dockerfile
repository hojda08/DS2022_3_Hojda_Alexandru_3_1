FROM alpine:3.16 AS build
RUN apk update && \
    apk upgrade && \
    apk add --update \
    ca-certificates \
    openjdk11  \
    maven && \
    rm -rf /var/cache/apk/*

FROM build AS compiledimg
WORKDIR /app
COPY ./src/ /app/src
COPY ./pom.xml /app/
COPY ./grpcServer.iml /app/
RUN mvn package

FROM build AS finalImg
COPY --from=compiledimg /app/target/grpcServer-1.0-SNAPSHOT.jar /app.jar

EXPOSE 9090
ENTRYPOINT ["java","-jar","/app.jar"]