package com.example.testapi.validator.helper;

import com.example.testapi.exception.ValidationFailureException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created By G900 on 12-Jan-18
 */

@Component("validationManager")
public class ValidationManager<T> implements ApplicationContextAware {
    private ApplicationContext applicationContext;


    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(ValidationManager.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public LocalValidatorFactoryBean getLocalValidatorFactoryBean() {
        return applicationContext.getBean("localValidatorFactory", LocalValidatorFactoryBean.class);
    }

    public void validate(T command) throws Exception {

        Set<ConstraintViolation<T>> constraintViolations = getLocalValidatorFactoryBean().validate(command);
        List<ErrorItem> errors = new ArrayList<>();
        for (Iterator<ConstraintViolation<T>> it = constraintViolations.iterator(); it.hasNext(); ) {
            ConstraintViolation cv = it.next();

            errors.add(new ErrorItem(cv.getMessageTemplate(), cv.getPropertyPath().toString()));
        }

        if (constraintViolations.size() > 0) {
            errors.stream().forEach(x -> LOG.info(String.format("%s - %s", x.getProperty(), x.getMessage())));
            throw new ValidationFailureException("There are validation errors", errors);

        }
    }
}


