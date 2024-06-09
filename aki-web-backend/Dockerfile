FROM openjdk:17-oracle

EXPOSE 8877

VOLUME /app

WORKDIR /app

RUN mkdir -p "./nginx/html/static/images"

ENTRYPOINT ["java", "-jar", "./app.jar", "--spring.profiles.active=pro"]
