package com.example.testapi.validator;

import com.example.testapi.command.GetMoviesByParametersCommand;
import com.example.testapi.helper.Genre;
import com.example.testapi.helper.MpaaRating;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Created By G900 on 12-Jan-18
 */
public class GetMoviesByParametersCommandValidator implements ConstraintValidator<ValidateGetMoviesByParametersCommand, GetMoviesByParametersCommand> {
    @Autowired
    CommonValidator commonValidator;

    @Override
    public void initialize(ValidateGetMoviesByParametersCommand constraintAnnotation) {

    }

    @Override
    public boolean isValid(GetMoviesByParametersCommand command, ConstraintValidatorContext context) {
        boolean isValid = true;

        if (!commonValidator.isNullOrEmpty(command.getMpaaRating())) {
            if (!commonValidator.isEnumValue(MpaaRating.class, command.getMpaaRating())) {
                isValid = false;
                context.buildConstraintViolationWithTemplate("Not Enum Value")
                        .addPropertyNode("mpaaRating")
                        .addConstraintViolation();
            }
        }

        if (!commonValidator.isNullOrEmpty(command.getGenres())) {
            for (String genre : command.getGenres()) {
                if (!commonValidator.isEnumValue(Genre.class, genre)) {
                    isValid = false;
                    context.buildConstraintViolationWithTemplate("Not Enum Value")
                            .addPropertyNode("genre")
                            .addConstraintViolation();
                }
            }
        }

        if (!commonValidator.isNullOrEmpty(command.getYear())) {
            // regex - 1900-2099
            if (!command.getYear().matches("^(19|20)\\d{2}$")) {
                isValid = false;
                context.buildConstraintViolationWithTemplate("Year should be in range 1900-2099")
                        .addPropertyNode("year")
                        .addConstraintViolation();
            }
        }
        return isValid;
    }
}
