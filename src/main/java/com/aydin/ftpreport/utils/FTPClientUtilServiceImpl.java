package com.aydin.ftpreport.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.google.inject.Singleton;

@Singleton
public class FTPClientUtilServiceImpl implements FTPClientUtilService
{

    private final String USER_NAME = "ftp-username";
    private final String PASSWORD = "ftp-password";
    private final int PORT = 21;
    private final String SERVER = "ftp-ip-address"; //must be ip v6

    private final String DIRECTORY = "destination folder path in ftp server";

    private FTPClient ftpClient = new FTPClient();



    public boolean sendToFTP(String filePath, String fileName)
    {
        boolean done = false;
        String newPath = initDirectoryName();

        try
        {
            ftpClient.connect(SERVER, PORT);
            ftpClient.login(USER_NAME, PASSWORD);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.changeWorkingDirectory(DIRECTORY);
            ftpClient.makeDirectory(newPath);
            ftpClient.changeWorkingDirectory(newPath);

            File localFile = new File(filePath);

            InputStream inputStream = new FileInputStream(localFile);

            done = ftpClient.storeFile(fileName, inputStream);
            System.out.println("success = " + done);
            inputStream.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        finally
        {
            try
            {
                if (ftpClient.isConnected())
                {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        return done;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aydin.ftpreport.utils.FTPClientUtilService#initDirectoryName()
     */
    public String initDirectoryName()
    {
        PersianCalendar pc = new PersianCalendar(new Date());

        String year = String.valueOf(pc.get(Calendar.YEAR));
        String mount = String.valueOf(pc.get(Calendar.MONTH) + 1);
        if (mount.length() < 2) mount = "0" + mount;
        String day = String.valueOf(pc.get(Calendar.DAY_OF_MONTH));

        return year + mount + day;
    }

}
