FROM openjdk:17-oracle

EXPOSE 8877

VOLUME /app

WORKDIR /app

RUN mkdir -p "./nginx/html/static/images"

ADD aki-web-backend-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch ./app.jar'

ENTRYPOINT ["java", "-jar", "./app.jar", "--spring.profiles.active=pro"]
