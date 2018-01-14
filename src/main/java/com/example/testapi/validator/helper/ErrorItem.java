package com.example.testapi.validator.helper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created By G900 on 12-Jan-18
 */

public class ErrorItem {
    private String message;
    private String property;

    @JsonCreator
    public ErrorItem(@JsonProperty("message") String message, @JsonProperty("property") String property) {
        this.message = message;
        this.property = property;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}


