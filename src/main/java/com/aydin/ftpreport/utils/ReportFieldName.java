package com.aydin.ftpreport.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * @author paakro@hotmail.com
 * 
 * filedName : field name in excel
 *
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReportFieldName
{
    String fieldName();

}


