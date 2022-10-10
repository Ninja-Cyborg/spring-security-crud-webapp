package com.spring.crmapp.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.spring.crmapp")
@PropertySource({"classpath:persistence-mysql.properties",
					"classpath:security-persistence-mysql.properties"})
public class CrmAppConfiguration implements WebMvcConfigurer {
	
	@Autowired
	private Environment env;
	
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	@Bean
	public DataSource dataSource1() {
		ComboPooledDataSource dataSource1 = new ComboPooledDataSource();
		
		try {
			dataSource1.setDriverClass("com.mysql.jdbc.Driver");
		}catch( PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		
		// access database connection  properties
		dataSource1.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource1.setUser(env.getProperty("jdbc.user"));
		dataSource1.setPassword(env.getProperty("jdbc.password"));
		
		// setting connection pools property
		dataSource1.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		dataSource1.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		dataSource1.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));		
		dataSource1.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return dataSource1;
	}
	
	private Properties getHibernateProperties() {
		
		Properties property = new Properties();
		property.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		property.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return property;
	}
	
	@Bean
	public DataSource securityDataSource() {
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		try {
		securityDataSource.setDriverClass(env.getProperty("security.jdbc.driver"));
		} catch (PropertyVetoException exc) {
		throw new RuntimeException(exc);
		}
		
		// database connection setup
		securityDataSource.setJdbcUrl(env.getProperty("security.jdbc.url"));
		securityDataSource.setUser(env.getProperty("security.jdbc.user"));
		securityDataSource.setPassword(env.getProperty("security.jdbc.password"));
		
		// connection pool setup
		securityDataSource.setInitialPoolSize(
				getIntProperty("security.connection.pool.initialPoolSize"));

		securityDataSource.setMinPoolSize(
				getIntProperty("security.connection.pool.minPoolSize"));

		securityDataSource.setMaxPoolSize(
				getIntProperty("security.connection.pool.maxPoolSize"));

		securityDataSource.setMaxIdleTime(
				getIntProperty("security.connection.pool.maxIdleTime"));
		
		return securityDataSource;
		
	}

	// helper method to read environment properties
	private int getIntProperty(String propertyName) {
		
		String propertyValue = env.getProperty(propertyName);
		int intPropertyValue = Integer.parseInt(propertyValue);
		
		return intPropertyValue;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// property setup
		sessionFactory.setDataSource(dataSource1());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		
		//setup transaction manager
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		
		return transactionManager;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}
}
