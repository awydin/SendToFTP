package com.aydin.ftpreport.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author paakro@hotmail.com
 * 
 * columns : number of export fielfs
 * reportName : file name
 * reportDesc : excel header  
 */


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReportInfo
{
    int columns() default 1;
    String reportName() default "report";
    String reportDesc() default "Header in excel"; 

}

