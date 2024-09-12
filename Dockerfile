# Step 1: Use OpenJDK 8 as base image
FROM openjdk:8-jdk-alpine

# Step 2: Set environment variables for Tomcat
ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
RUN mkdir -p "$CATALINA_HOME"
WORKDIR $CATALINA_HOME

# Step 3: Download Tomcat 9 and extract it
RUN wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.64/bin/apache-tomcat-9.0.64.tar.gz
RUN tar xvf apache-tomcat-9.0.64.tar.gz
RUN mv apache-tomcat-9.0.64/* $CATALINA_HOME

# Step 4: Copy the Spring MVC WAR file to Tomcat's webapps directory
COPY target/MiniProject.war $CATALINA_HOME/webapps/

# Step 5: Expose port 8080
EXPOSE 8080

# Step 6: Start Tomcat
CMD ["catalina.sh", "run"]
