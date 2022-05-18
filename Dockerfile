FROM maven:3-jdk-11
WORKDIR .
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run -Dspring-boot.run.profiles=prod
