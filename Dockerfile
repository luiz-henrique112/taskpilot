# -------- BUILD STAGE --------
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .
RUN mvn clean package 


# -------- RUNTIME STAGE --------
FROM tomcat:10.1-jdk21-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/taskpilot.war /usr/local/tomcat/webapps/ROOT.war

CMD ["sh", "-c", "sed -i \"s/8080/${PORT}/\" /usr/local/tomcat/conf/server.xml && catalina.sh run"]
