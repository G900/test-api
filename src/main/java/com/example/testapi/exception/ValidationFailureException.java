package com.example.testapi.exception;

import com.example.testapi.validator.helper.ErrorItem;
import java.util.List;

/**
 * Created By G900 on 12-Jan-18
 */
public class ValidationFailureException extends RuntimeException {

    private List<ErrorItem> errors;

    public ValidationFailureException(String message, List<ErrorItem> errors) {
        super(message);
        this.errors = errors;
    }

    public List<ErrorItem> getErrors() {
        return errors;
    }
}
