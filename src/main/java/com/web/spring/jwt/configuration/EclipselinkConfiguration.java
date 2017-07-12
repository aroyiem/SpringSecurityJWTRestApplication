/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.spring.jwt.configuration;

import java.util.Properties;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author subho
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.web.spring.jwt.configuration"})
@EnableJpaRepositories("com.web.spring.jwt.repo")
@PropertySource({"classpath:application.properties"})
public class EclipselinkConfiguration {
    
    @Autowired
    private Environment environment;
    /*    
    configure dataSource using JNDI
    */
    @Bean
    public DataSource datasource() throws NamingException{
        return (DataSource)new JndiTemplate().lookup("java:comp/env/jdbc/springsecurity");
    }
    
    /*
    provide Jpa vendor Adapter (Hibernate/Eclipselink)    
    */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
        return adapter;
    }
    
    /*
    configure entitymanagerfactory    
    */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException{
        
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("springsecurity");
        em.setDataSource(datasource());
        em.setJpaVendorAdapter(jpaVendorAdapter());
        em.setPackagesToScan(new String[]{"com.web.spring.jwt.entities"});
        em.setJpaProperties(jpaProperties());
        return em;
    }
    
    /*
     * Here you can specify any provider specific properties.
     */
    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("eclipselink.target-database", environment.getRequiredProperty("eclipselink.target-database"));
        properties.put("eclipselink.jdbc.timeout", environment.getRequiredProperty("eclipselink.jdbc.timeout"));
        properties.put("eclipselink.logging.level", environment.getRequiredProperty("eclipselink.logging.level"));
        properties.put("eclipselink.weaving", environment.getRequiredProperty("eclipselink.weaving"));
        properties.put("eclipselink.id-validation", environment.getRequiredProperty("eclipselink.id-validation"));
        properties.put("javax.persistence.validation.mode", environment.getRequiredProperty("javax.persistence.validation.mode"));

        return properties;
    }
    
    /*
    set Transaction manager    
    */
    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }
}
