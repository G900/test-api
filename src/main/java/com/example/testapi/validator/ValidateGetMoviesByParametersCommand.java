package com.example.testapi.validator;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created By G900 on 12-Jan-18
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GetMoviesByParametersCommandValidator.class)
public @interface ValidateGetMoviesByParametersCommand {
    String message() default  "Invalid get movies command";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
