## GIN - Gestão de Inventário

Sistema para gestão dos ativos que estão e serão adquiridos pela Universidade Federal do Ceará campus Quixadá.

## Fornecer nome JNDI do datasource: (gin)

No arquivo server.xml...

```xml
...
<GlobalNamingResources>
    ...
    <Resource auth="Container" driverClassName="org.postgresql.Driver" maxActive="10" maxIdle="3" maxWait="10000" name="gin" password="senha" type="javax.sql.DataSource" url="jdbc:postgresql://localhost/gin" username="usuario"/>
</GlobalNamingResources>
...
```

No arquivo context.xml...

```xml
...
<Context>
  ...
  <ResourceLink global="gin" name="gin" type="javax.sql.DataSource"/>
</Context>
...
```

## Importante

Copiar o driver do PostgreSQL (postgresql-xxx.jar - http://mvnrepository.com/artifact/org.postgresql/postgresql) para o diretório de libs do Tomcat.
