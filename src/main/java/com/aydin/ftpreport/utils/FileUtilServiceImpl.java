package com.aydin.ftpreport.utils;

import java.io.File;

import com.google.inject.Singleton;

@Singleton
public class FileUtilServiceImpl implements FileUtilService
{

    public String getFolderPath()
    {
        final String userHome = System.getProperty("user.home");
        final String folderPath = "/export-folder" ;
        
        File file = new File( userHome + folderPath );
        if (!file.exists())
        {
            file.mkdir();
        }
        
        return userHome + folderPath;
    }
}
