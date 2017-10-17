# SendToFTP
### Create an excel file from an existing database view and send it to your ftp server 



#### Build command
`mvn clean install`


#### How to use
1. create an database view that you would create excel from
2. create an entity and map it to your database view
3. add @ReportInfo on top of your entity class
4. add @ReportFieldName on top of fields you expect to export
5. add your ftp server configuration to FTPClientUtilServiceImpl
6. run .jar file in target folder
7. enjoy ;)



