package com.example.recepcja;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration(exclude={
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class
})
public class PlikKonfiguracyjny {
    @Autowired
    private Environment environment;

    @Bean(name="dataSource")
    public DataSource getDataSource(){
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        source.setUrl(environment.getProperty("spring.datasource.url"));
        source.setUsername(environment.getProperty("spring.datasource.username"));
        source.setPassword(environment.getProperty("spring.datasource.password"));
        return source;
    }

    @Autowired
    @Bean(name="sessionFactory")
    public SessionFactory sessionFactory(DataSource dataSource) throws Exception{
        Properties properties = new Properties();
        properties.put("hibernate.dialect",environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("current_session_context_class",environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
        LocalSessionFactoryBean local = new LocalSessionFactoryBean();
        local.setPackagesToScan(new String[]{""});
        local.setDataSource(dataSource);
        local.setHibernateProperties(properties);
        local.afterPropertiesSet();
        SessionFactory sessionFactory = local.getObject();
        return sessionFactory;
    }

    @Autowired
    @Bean(name="transactionManager")
    public HibernateTransactionManager manager(SessionFactory sessionFactory){
        HibernateTransactionManager manager = new HibernateTransactionManager(sessionFactory);
        return  manager;
    }
}
