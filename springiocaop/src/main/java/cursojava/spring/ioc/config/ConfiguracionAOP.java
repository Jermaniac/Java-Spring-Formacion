package cursojava.spring.ioc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"cursojava.spring.aop.aspectos","cursojava.spring.ioc.beans"})
public class ConfiguracionAOP {
	
	

}
