package com.aydin.ftpreport.utils;

import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aydin.injector.ApplicationInjector;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class ExcelUtilServiceImpl implements ExcelUtilService
{

    private Injector injector = Guice.createInjector(new ApplicationInjector());
    private FileUtilService fileUtilService = injector.getInstance(FileUtilService.class);


    public <T> Map<Long, String> initExcel(List<T> resultList, Class<T> entityClass) throws IllegalArgumentException, IllegalAccessException
    {
        Map<Long, String> resultMap = new HashMap<Long, String>();

        Map<Long, String> map = fetchAnotationInfo(entityClass);
        String fileName = map.get(2L);
        String fileDesc = map.get(3L);
        Long columns = Long.valueOf(map.get(1L));

        int rowNum = 0;
        int colNum = 0;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(fileDesc);
        Row row = sheet.createRow(rowNum);
        sheet.getCTWorksheet().getSheetViews().getSheetViewArray(0).setRightToLeft(true); // init
                                                                                          // sheet
                                                                                          // rightToLeft

        // Styling the cell
        XSSFCellStyle headerStyle = initStyle("header", workbook);
        XSSFCellStyle normalStyle = initStyle("normal", workbook);

        // report title
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columns.intValue() - 1));
        Cell cell = row.createCell(colNum);
        cell.setCellStyle(headerStyle);
        cell.setCellValue(fileDesc);

        Field[] fields = entityClass.getDeclaredFields();

        row = sheet.createRow(++rowNum);
        row = sheet.createRow(++rowNum);
        int cellNum = 0;
        for (Field field : fields)
        {

            if (field.isAnnotationPresent(ReportFieldName.class))
            {
                Cell cell2 = row.createCell(cellNum++);
                cell2.setCellStyle(headerStyle);

                ReportFieldName reportField = field.getAnnotation(ReportFieldName.class);

                cell2.setCellValue(reportField.fieldName());

            }

        }

        for (T data : resultList)
        {
            row = sheet.createRow(++rowNum);

            cellNum = 0;
            for (Field field : fields)
            {

                if (field.isAnnotationPresent(ReportFieldName.class))
                {
                    Cell cell2 = row.createCell(cellNum++);
                    cell2.setCellStyle(normalStyle);

                    field.setAccessible(true);

                    String fieldData = field.get(data) != null ? field.get(data).toString() : " ";
                    cell2.setCellValue(fieldData);

                    cell2 = null;

                }

            }
        }

        fileName = fileName + ".xlsx";

        String path = fileUtilService.getFolderPath() + fileName;

        try
        {
            FileOutputStream outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            outputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        resultMap.put(1L, path);
        resultMap.put(2L, fileName);
        return resultMap;
    }

    private <T> Map<Long, String> fetchAnotationInfo(Class<T> entityClass)
    {
        Map<Long, String> map = new HashMap<Long, String>();
        Class<T> obj = entityClass;

        if (obj.isAnnotationPresent(ReportInfo.class))
        {

            Annotation annotation = obj.getAnnotation(ReportInfo.class);
            ReportInfo reportInfo = (ReportInfo) annotation;
            map.put(1L, String.valueOf(reportInfo.columns()));
            map.put(2L, reportInfo.reportName());
            map.put(3L, reportInfo.reportDesc());
        }

        return map;
    }

    private XSSFCellStyle initStyle(String cellType, XSSFWorkbook workbook)
    {
        XSSFCellStyle style = workbook.createCellStyle();

        if (cellType.equals("header"))
        {
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setBorderBottom(Short.valueOf("2"));
            style.setBorderLeft(Short.valueOf("2"));
            style.setBorderRight(Short.valueOf("2"));
            style.setBorderTop(Short.valueOf("2"));
        }
        else
        {
            style.setAlignment(HorizontalAlignment.CENTER);
        }

        return style;
    }

}
