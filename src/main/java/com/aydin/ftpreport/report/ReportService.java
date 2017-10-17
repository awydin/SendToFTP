package com.aydin.ftpreport.report;

import java.util.List;

public interface ReportService
{

    <T> List<T> findReport(Class<T> entityClass);
    public <T> void generateReport(Class<T> entityClass) throws IllegalArgumentException, IllegalAccessException ;

}