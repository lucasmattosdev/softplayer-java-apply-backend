# Softplayer Java Apply - Backend

Aplicação para o teste prático de cargo Desenvolvedor Fullstack na Softplan, desenvolvida com Rest API e Hibernate utilizando banco de dados Postgresql.

## Desafio

CRUD de Pessoa com autenticação Basic.

```bash
pip install foobar
```

## Aplicação

Para rodar, devemos compilar o projeto com o comando:
```bash
mvn clean install
```
No projeto há um backup do banco para ser restaurado. Devemos configurar o DataSource no servidor (Wildfly, Tomcat, outros) como no exemplo abaixo. Caso não possua o driver do Postgresql, baixe "postgresql-42.2.14.jar" e suba junto.
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

Obs: Veja o módulo [Frontend](https://github.com/lucasmattooos/softplayer-java-apply-frontend) deste projeto para complementar o backend e levantar a interface do usuário.

## License
© 2021 [Lucas Mattos](https://www.lucasmattos.com.br/). Todos os direitos reservados.
