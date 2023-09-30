FROM amazoncorretto:21-alpine-jdk

ENV TOMCAT_RELEASE 11.0.0-M11
ENV TOMCAT_RELEASE_SHA512 0404346b353030f776448af8ae4495d45c4c73c27bf9a9bfa5c53508ec51b0200e93e21f5526a28856639127fc20cc546e880908ef182b1653e5fd5ddb056fc5
ENV CATALINA_HOME /usr/local/apache-tomcat
ENV PATH $CATALINA_HOME/bin:$PATH

RUN uname -r
RUN java -version
RUN apk --no-cache add --update bash

RUN mkdir -p /usr/local/
WORKDIR /usr/local/

RUN wget https://dlcdn.apache.org/tomcat/tomcat-11/v${TOMCAT_RELEASE}/bin/apache-tomcat-${TOMCAT_RELEASE}.tar.gz -O /tmp/catalina.tar.gz \
    && echo "$TOMCAT_RELEASE_SHA512  /tmp/catalina.tar.gz" | sha512sum -c \
    && tar xzf /tmp/catalina.tar.gz -C /usr/local/ && mv /usr/local/apache-tomcat-${TOMCAT_RELEASE} /usr/local/apache-tomcat && rm /tmp/catalina.tar.gz

RUN apk add shadow
RUN rm -rf ${CATALINA_HOME}/webapps && mkdir -p ${CATALINA_HOME}/webapps
RUN rm -rf /tmp/* /var/cache/apk/*

RUN groupadd -r tomcat && useradd -r -g tomcat tomcat && chown -R tomcat:tomcat /usr/local/apache-tomcat

## build the servlet
RUN mkdir -p /usr/local/build/src/servlet
RUN mkdir -p /usr/local/build/web/WEB-INF/classes/servlet
RUN chown -R tomcat:tomcat /usr/local/build/
COPY --chown=tomcat:tomcat src/servlet/GetThread.java /usr/local/build/src/servlet/GetThread.java
COPY --chown=tomcat:tomcat web/ /usr/local/build/web/
RUN javac -d /usr/local/build/web/WEB-INF/classes/ -cp ${CATALINA_HOME}/lib/\* /usr/local/build/src/servlet/*.java
RUN jar cvf Benchmark.war -C /usr/local/build/web/ .
RUN mv Benchmark.war ${CATALINA_HOME}/webapps
RUN rm -rf /usr/local/build/
## // end build the servlet

COPY --chown=tomcat:tomcat conf/server.xml ${CATALINA_HOME}/conf/server.xml

USER tomcat

CMD ["catalina.sh", "run"]