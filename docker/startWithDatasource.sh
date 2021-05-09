#!/bin/bash

if [ ! -f wildfly.started ]; then
JBOSS_CLI=$WILDFLY_HOME/bin/jboss-cli.sh

function espera_levantar_servidor() {
  until `$JBOSS_CLI -c "ls /deployment" &> /dev/null`; do
    echo "Esperando"
    sleep 1
  done
}

echo "=> Iniciando servidor WildFly"
$WILDFLY_HOME/bin/standalone.sh -b=0.0.0.0 -c standalone.xml > /dev/null &

echo "=> Esperando servidor iniciar"
espera_levantar_servidor

echo "=> Configurar Datasource"
$JBOSS_CLI -c << EOF
batch

# Adicionar driver PostgreSQL
module add --name=org.postgres --resources=/tmp/postgresql-42.2.8.jar --dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)

# Adicionar datasource
data-source add \
  --jndi-name=$DATASOURCE_JNDI \
  --name=$DATASOURCE_NAME \
  --connection-url=jdbc:postgresql://$DB_HOST:$DB_PORT/$DB_NAME \
  --driver-name=postgres \
  --user-name=$DB_USER \
  --password=$DB_PASS \
  --check-valid-connection-sql="SELECT 1" \
  --background-validation=true \
  --background-validation-millis=60000 \
  --flush-strategy=IdleConnections \
  --min-pool-size=10 --max-pool-size=100  --pool-prefill=false

# Executa o batch
run-batch
EOF

FILES=$CLI_DIR/*.cli
for f in $FILES
do
  echo "Processando $f arquivo..."
  $JBOSS_CLI -c --file=$f
done

echo "=> Parar Wildfly"
$JBOSS_CLI -c ":shutdown"

echo "=> Deploy ear"
cp ${DEPLOY_DIR}/* ${WILDFLY_HOME}/standalone/deployments/

touch wildfly.started
fi

echo "=> Iniciando Wildfly"
$WILDFLY_HOME/bin/standalone.sh -b=0.0.0.0 -c standalone.xml
