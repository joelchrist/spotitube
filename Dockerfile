FROM davidcaste/alpine-tomcat:tomcat8
COPY target/spotitube.war /opt/tomcat/webapps/webapp-jdbc.war
CMD ["/opt/tomcat/bin/catalina.sh", "run"]