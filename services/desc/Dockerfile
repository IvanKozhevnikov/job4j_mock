FROM maven:3.6.3-openjdk-17

EXPOSE 8080

RUN mkdir desc

WORKDIR desc

COPY . .

RUN mvn package -Dmaven.test.skip=true

CMD ["mvn", "liquibase:update", "-Pdocker"]

CMD ["java", "-jar", "target/desc.jar"]