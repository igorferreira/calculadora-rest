# calculadora-rest

Este projeto serve como exemplo para fazer deploy no appengine do google um projeto com springboot

Passos feitos para configurar

## Criar class ServletInitializer

```java
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CalculadoraApplication.class);
	}

}

```

## No pom.xml alterar os seguintes pontos


* passo 1 - remover tomcat pois o appengine usa jetty

```xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
		<exclusions>
			<exclusion>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-tomcat</artifactId>
			</exclusion>
		</exclusions>
	</dependency>
```

* passo 2 - remover devtools pois incompativel

```xml
		<!-- Incompativel com appengine -->
		<!-- <dependency> -->
		<!-- <groupId>org.springframework.boot</groupId> -->
		<!-- <artifactId>spring-boot-devtools</artifactId> -->
		<!-- <scope>runtime</scope> -->
		<!-- </dependency> -->
```

* passo 3 - adicionar dependencia javax.servlet-api por causa do jetty

```xml
	<dependency>
		<groupId>javax.servlet</groupId>
		<artifactId>javax.servlet-api</artifactId>
		<scope>provided</scope>
	</dependency>
```

* passo 4 - excluir dependencia jul-to-slf4j colocando como provided
	
```xml	
	<!-- Exclude any jul-to-slf4j -->
	<dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>jul-to-slf4j</artifactId>
		<scope>provided</scope>
	</dependency>
```

* passo 5 - adicionar o repackage por causa do war no plugin spring-boot-maven 
* passo 6 - adicionar flag para nao falhar na falta do web.xml no plugin spring-boot-maven por causa do war
			
```xml
	<plugin>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-maven-plugin</artifactId>
		<executions>
			<execution>
				<goals>
					<goal>repackage</goal>
				</goals>
			</execution>
		</executions>
		<configuration>
			<failOnMissingWebXml>false</failOnMissingWebXml>
		</configuration>
	</plugin>
```
* passo 7- adicionar plugin do google cloud tools

```xml
	<!-- [START cloudplugin] -->
	<plugin>
		<groupId>com.google.cloud.tools</groupId>
		<artifactId>appengine-maven-plugin</artifactId>
		<version>1.3.2</version>
		<configuration>
		</configuration>
	</plugin>
	<!-- [END cloudplugin] -->
```

* inves do plugin cloud tools, pode ser usado o appengine plugin:
	
```xml
	<plugin>
		<groupId>com.google.appengine</groupId>
			<artifactId>appengine-maven-plugin</artifactId>
		<version>1.9.64</version>
		<configuration>
		</configuration>
	</plugin>
```

## Para testar execute

* com o pluing ``com.google.cloud.tools```  use:

```sh
 mvn clean appengine:run

```

* com o pluing ```com.google.appengine ```  use:

Para operar o servidor de teste

```sh
 mvn clean appengine:devserver
```

```sh
 mvn clean appengine:devserver_stop 
```


## Para deploy

* com o pluing ```com.google.cloud.tools```  use:

```sh
 mvn clean appengine:deploy

```
* com o pluing ```com.google.appengine```  use:

```sh
 mvn clean appengine:update
```

