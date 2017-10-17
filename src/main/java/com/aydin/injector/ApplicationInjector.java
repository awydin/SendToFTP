package com.aydin.injector;


import com.aydin.ftpreport.data.HIbernateUtilServiceImpl;
import com.aydin.ftpreport.data.HibernateUtilService;
import com.aydin.ftpreport.report.ReportService;
import com.aydin.ftpreport.report.ReportServiceImpl;
import com.aydin.ftpreport.utils.ExcelUtilService;
import com.aydin.ftpreport.utils.ExcelUtilServiceImpl;
import com.aydin.ftpreport.utils.FTPClientUtilService;
import com.aydin.ftpreport.utils.FTPClientUtilServiceImpl;
import com.aydin.ftpreport.utils.FileUtilService;
import com.aydin.ftpreport.utils.FileUtilServiceImpl;
import com.google.inject.AbstractModule;

public class ApplicationInjector extends AbstractModule
{

    @Override
    protected void configure()
    {
        bind(FileUtilService.class).to(FileUtilServiceImpl.class);
        bind(HibernateUtilService.class).to(HIbernateUtilServiceImpl.class);
        bind(ReportService.class).to(ReportServiceImpl.class);
        bind(ExcelUtilService.class).to(ExcelUtilServiceImpl.class);
        bind(FTPClientUtilService.class).to(FTPClientUtilServiceImpl.class);
        
        
    }

}
