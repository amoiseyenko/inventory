package com.aspiderngi.inventory.web.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.aspiderngi.inventory")
@PropertySource({"classpath:hibernate.properties" })
public class AppContextConfiguration {

    @Autowired
    private Environment env;
	 
    @Bean
    public DataSource dataSource() {
    	 BasicDataSource dataSource = new BasicDataSource();
         dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
         dataSource.setUrl(env.getProperty("jdbc.url"));
         dataSource.setUsername(env.getProperty("jdbc.username"));
         dataSource.setPassword(env.getProperty("jdbc.password"));
    
         return dataSource;
    }
    
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(sessionFactory);
       return txManager;
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
       LocalSessionFactoryBean sessionFactoryBuilder = new LocalSessionFactoryBean();
       sessionFactoryBuilder.setDataSource(dataSource());
       sessionFactoryBuilder.setPackagesToScan(new String[] { "com.aspiderngi.inventory" });
       sessionFactoryBuilder.setHibernateProperties(hibernateProperties());
  
       return sessionFactoryBuilder;
    }
	
    @SuppressWarnings("serial")
	Properties hibernateProperties() {
        return new Properties() {
           {
        	  System.out.println(env);
              setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
              setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
              setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
              setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
              setProperty("hibernate.hbm2ddl.import_files_sql_extractor", env.getProperty("hibernate.hbm2ddl.import_files_sql_extractor"));
           }
        };
     }
}