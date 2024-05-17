package com.ra.baitapsession18_orm.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ra.baitapsession18_orm")
public class AppConfig implements WebMvcConfigurer, ApplicationContextAware
{
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver resolver()
    {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("/views/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("utf-8");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine()
    {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver()
    {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("utf-8");
        return viewResolver;
    }

    @Bean
    public DataSource getDataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_bai18orm");
        dataSource.setUsername("root");
        dataSource.setPassword("Blackpigsql666");
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory()
    {
        LocalSessionFactoryBean sessionBean = new LocalSessionFactoryBean();
        sessionBean.setDataSource(getDataSource());
        sessionBean.setPackagesToScan("com.ra.baitapsession18_orm.entity");
        Properties props = new Properties();
        props.setProperty("hibernate.show_sql", "true");
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        sessionBean.setHibernateProperties(props);
        try
        {
            sessionBean.afterPropertiesSet();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return sessionBean.getObject();
    }
}
