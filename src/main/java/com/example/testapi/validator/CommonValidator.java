package com.example.testapi.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * Created By G900 on 12-Jan-18
 */
@Component
public class CommonValidator {
    public boolean isNullOrEmpty(Object o) {
        if (o == null) {
            return true;
        } else if (o instanceof String) {
            return StringUtils.isEmpty(o);
        } else return false;

    }

    public boolean isGreaterOrEqualsThanValid(Integer min, Integer value) {

        return value.intValue() >= min.intValue();

    }

    public <T extends Enum<?>> boolean isEnumValue(Class<T> enumClass, String value) {
        return Arrays.asList(enumClass.getEnumConstants()).stream().anyMatch(x -> x.name().equalsIgnoreCase(value));
    }
}
