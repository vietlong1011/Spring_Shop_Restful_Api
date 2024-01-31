FROM openjdk:17
VOLUME /tmp

#Config DB
ENV DATABASE_HOST=localhost
ENV DATABASE_PORT=3306
ENV DATABASE_NAME=shop_restful
ENV DATABASE_USERNAME=root
ENV DATABASE_PASSWORD=10112002

COPY target/*.jar shop.jar
LABEL authors="NONG"

ENTRYPOINT ["java","-jar", "/shop.jar"]
