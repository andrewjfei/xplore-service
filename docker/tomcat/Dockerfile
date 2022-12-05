FROM tomcat:10.1.2-jdk17

# Copy xplore-service war file to Tomcat webapp directory to startup on init.
COPY target/ROOT.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]
