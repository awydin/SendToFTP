package com.aydin.ftpreport.utils;

import java.util.List;
import java.util.Map;

public interface ExcelUtilService
{

    <T> Map<Long, String> initExcel(List<T> resultList, Class<T> entityClass) throws IllegalArgumentException, IllegalAccessException;

}