package com.example.testapi;

import com.example.testapi.validator.helper.ValidationManager;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created By G900 on 13-Jan-18
 */
@Configuration
public class TestConfig {

    @Bean(name = "localValidatorFactory")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;

    }

    @Bean
    public ValidationManager getValidationManager() {
        return new ValidationManager();
    }



}