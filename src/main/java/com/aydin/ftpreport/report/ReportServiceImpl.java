package com.aydin.ftpreport.report;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.aydin.ftpreport.data.HibernateUtilService;

import com.aydin.ftpreport.utils.ExcelUtilService;

import com.aydin.ftpreport.utils.FTPClientUtilService;
import com.aydin.injector.ApplicationInjector;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class ReportServiceImpl implements ReportService
{

    Injector injector = Guice.createInjector(new ApplicationInjector());
    HibernateUtilService hibernateUtilService = injector.getInstance(HibernateUtilService.class);
    ExcelUtilService excelUtilService = injector.getInstance(ExcelUtilService.class);
    FTPClientUtilService ftpClientService = injector.getInstance(FTPClientUtilService.class);

    public <T> List<T> findReport(Class<T> entityClass)
    {
        Session session = hibernateUtilService.getSessionfactory().openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(entityClass);
        List<T> results = criteria.list();

        return results;

    }

    public <T> void generateReport(Class<T> entityClass) throws IllegalArgumentException, IllegalAccessException
    {

       

        List<T> list = findReport(entityClass);
        if (list != null)
        {

            Map<Long, String> map = excelUtilService.initExcel(list, entityClass);
            if (!ftpClientService.sendToFTP(map.get(1L), map.get(2L)))
            {
                System.out.println("something went wrong whit " + entityClass);
            }

        }
    }

}
