# Softplayer Java Apply - Backend

Aplicação para o teste prático de cargo Desenvolvedor Fullstack na Softplan, desenvolvida com Rest API e Hibernate utilizando banco de dados Postgresql.

## Desafio

CRUD de Pessoa com autenticação Basic.

```bash
pip install foobar
```

## Execução

Utilize Wildfly como servidor Web a ser utilizado por esta aplicação.

No projeto há o script `docker/init.sql` que contem toda a estrutura do banco a ser utilizada por este sistema.

Caso não possua o driver do Postgresql, utilize o driver localizado em `docker/postgresql-42.2.14.jar` e suba junto no servidor.
Devemos configurar o Datasource como no exemplo abaixo:
```xml
<datasource jndi-name="java:jboss/datasources/SoftplayerJavaApplyDS" pool-name="SoftplayerJavaApplyDS" use-ccm="false">
                    <connection-url>jdbc:postgresql://localhost:5432/softplayer_java_apply</connection-url>
                    <driver>postgresql-42.2.14.jar</driver>
                    <security>
                        <user-name>USUARIOBANCO</user-name>
                        <password>SENHABANCO</password>
                    </security>
                </datasource>
```

Para rodar, devemos compilar o projeto com `MAVEN`:
```bash
mvn clean install
```
Após compilado, basta subir o EAR no servidor Wildfly, localizado em `softplayer-java-apply-backend-ear/target/softplayer-java-apply-backend-ear.ear`

Obs: Veja o módulo [Frontend](https://github.com/lucasmattooos/softplayer-java-apply-frontend) deste projeto para complementar o backend e levantar a interface do usuário.

## License
© 2021 [Lucas Mattos](https://www.lucasmattos.com.br/). Todos os direitos reservados.
