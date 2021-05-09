FROM jboss/wildfly:20.0.0.Final
MAINTAINER Lucas Mattos

ENV WILDFLY_HOME /opt/jboss/wildfly
ENV DEPLOY_DIR ${WILDFLY_HOME}/standalone/deployments/

# setup timezone
ENV TZ=America/Sao_Paulo
USER root
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
USER jboss

ENV DATASOURCE_NAME SoftplayerJavaApplyDS
ENV DATASOURCE_JNDI java:jboss/datasources/SoftplayerJavaApplyDS

ENV DB_HOST postgres
ENV DB_PORT 5432
ENV DB_USER production
ENV DB_PASS swJCwVLlHZ3R
ENV DB_NAME softplayer_java_apply

# create temporary deployment dir, because wars can deploy after the datasource is created
RUN mkdir /tmp/deploments
ENV DEPLOY_DIR /tmp/deploments
COPY softplayer-java-apply-backend-ear.ear $DEPLOY_DIR/softplayer-java-apply-backend-ear.ear

RUN mkdir /tmp/jboss-cli
ENV CLI_DIR /tmp/jboss-cli

COPY startWithDatasource.sh $WILDFLY_HOME/bin

USER root
RUN chown jboss:jboss $WILDFLY_HOME/bin/startWithDatasource.sh
RUN chmod 755 $WILDFLY_HOME/bin/startWithDatasource.sh
USER jboss

COPY postgresql-42.2.8.jar /tmp

ENTRYPOINT $WILDFLY_HOME/bin/startWithDatasource.sh
