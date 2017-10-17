package com.aydin.ftpreport.utils;

public interface FTPClientUtilService
{

    boolean sendToFTP(String filePath, String fileName);

    /**
     * 
     * create folder with today date name
     */
    String initDirectoryName();

}