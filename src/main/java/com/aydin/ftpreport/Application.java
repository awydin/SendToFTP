package com.aydin.ftpreport;



import com.aydin.ftpreport.entities.AllCouponsReportEntity;
import com.aydin.ftpreport.report.ReportService;

import com.aydin.injector.ApplicationInjector;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Application
{

    private static Injector injector = Guice.createInjector(new ApplicationInjector());
    private static ReportService reportService = injector.getInstance(ReportService.class);
    

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException
    {
        
        final long startTime = System.currentTimeMillis();

        
        reportService.generateReport(AllCouponsReportEntity.class);
        
        
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) );
        
        
        System.exit(0);

    }

    

}
