package com.kejeiri.springproject.complaintsystem;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
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

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:database-properties.properties"})
@ComponentScan({"om.kejeiri.springproject.complaintsystem"})
public class PersistenceConfig {
	
	@Autowired
	private Environment env;
	@Bean LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] {"com.kejeiri.springproject.complaintsystem.entities"});
		sessionFactory.setHibernateProperties(hibernateConfig());
		return sessionFactory;
	}
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driveClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
		}
	
	@Bean
	@Autowired 
	public HibernateTransactionManager  transactionManager (SessionFactory sessionFactory) {
		HibernateTransactionManager  transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}

	
	Properties hibernateConfig() {
		Properties myConfig= new Properties();
		myConfig.setProperty("hibernate.hdm2ddl.auto", env.getProperty("hibernate.hdm2ddl.auto"));
		myConfig.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		myConfig.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return myConfig;		
	}
	

}
