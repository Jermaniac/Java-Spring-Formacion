package cursojava.practicaspring.german.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

@Configuration(proxyBeanMethods = false)
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = {"cursojava.practicaspring.german.repositorios"},
		entityManagerFactoryRef = "entityManagerFactory",
		transactionManagerRef = "transactionManager"
		)
@ComponentScan(basePackages = "cursojava.practicaspring.german.datos")
public class ConfiguracionAccesoDatos {

	private String url;
	private String user;
	private String password;
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Indicamos el bean correspondiente al datasource e indicamos primary
	@Bean
	@Primary
	public DataSource dataSource() {
		
		MysqlConnectionPoolDataSource mysqlPool = new MysqlConnectionPoolDataSource();
		
		mysqlPool.setUrl("jdbc:mysql://localhost:3306/DEMOS");
		mysqlPool.setUser("root");
		mysqlPool.setPassword("Fedora2020");
		
//		mysqlPool.setUrl(url);
//		mysqlPool.setUser(user);
//		mysqlPool.setPassword(password);
		
		return mysqlPool;
		
	}
	
	// Aqui utilizamos el bean que incorpora la interaccion con JPA
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource,
			EntityManagerFactoryBuilder builder
			) {
		
		return builder.dataSource(dataSource)
		.packages("cursojava.practicaspring.german.datos.entidades")
		.persistenceUnit("demos")
		.build();
		
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager(entityManagerFactory.getObject());
		return txManager;
	}
	
}
