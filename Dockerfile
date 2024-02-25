FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true
EXPOSE 8082
ENV AUTHENTICATION_DATABASE=admin
ENV USERNAME=sa
ENV PASSWORD=teste@123
ENV DATABASE=fasteatsdb_cozinha
ENV PORT=27017
ENV HOST=localhost
ENV URL_PEDIDO_SERVICE='http://localhost:8082'
ENV URL_COZINHA_PEDIDO_SERVICE='http://localhost:8083'
ENTRYPOINT ["java", "-jar","/home/app/target/api-producao-1.0.0-SNAPSHOT.jar"]