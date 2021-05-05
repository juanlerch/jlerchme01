FROM gcr.io/google-appengine/jetty
ADD target/jlerchme-0.1.1-SNAPSHOT.war $JETTY_BASE/webapps/root.war
WORKDIR $JETTY_BASE
RUN java -jar $JETTY_HOME/start.jar --approve-all-licenses --add-to-startd=jmx,stats,hawtio \ 
  && chown -R jetty:jetty $JETTY_BASE