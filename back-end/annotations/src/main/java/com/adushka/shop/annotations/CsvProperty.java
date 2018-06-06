package com.adushka.shop.annotations;


import com.adushka.shop.enums.PropertyType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CsvProperty {
    PropertyType propertyType();
    int columnNumber() default 0;
    String keyField() default "null";
}
