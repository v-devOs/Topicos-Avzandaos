package com.example.todolistapp.reports;

import com.example.todolistapp.models.Task;
import db.dao.TaskDao;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

public class ExcelReport {

    TaskDao taskDao = new TaskDao();

    public void createExcel( String dest ){
        try(XSSFWorkbook workbook = new XSSFWorkbook()){

            XSSFSheet sheet1 = workbook.createSheet("Tasks List");

            // Creation of title
            XSSFRow row0 = sheet1.createRow(0);
            XSSFCell cellTitle = row0.createCell(0);
            cellTitle.setCellValue("Tasks List from Todo");
            sheet1.addMergedRegion(new CellRangeAddress(0,0,0,4));

            // Creation of styles title
            XSSFCellStyle styleTitle = workbook.createCellStyle();
            styleTitle.setFont(createTitleFont(sheet1));
            styleTitle.setVerticalAlignment(VerticalAlignment.CENTER);
            styleTitle.setAlignment(HorizontalAlignment.CENTER);
            cellTitle.setCellStyle(styleTitle);

            // Creation of body
            Map<Task, Integer> taskList = taskDao.getTaskToExcelReport();
            AtomicInteger rowNum = new AtomicInteger(2);

            createHeadersCells(sheet1);

            taskList.forEach(( key, value ) -> {
               XSSFRow row = sheet1.createRow(rowNum.getAndIncrement());
               XSSFCell cellNoTask = row.createCell(0);
               XSSFCell cellTitleTask = row.createCell(1);
               XSSFCell cellDescTask = row.createCell(2);
               XSSFCell cellStatusTask = row.createCell(3);
               XSSFCell cellDueDateTask = row.createCell(4);
               XSSFCell cellLabel = row.createCell(5);

               cellNoTask.setCellValue(key.getId());
               cellTitleTask.setCellValue(key.getName());
               cellDescTask.setCellValue(key.getDescription());
               cellStatusTask.setCellValue(key.getStatus());
               cellDueDateTask.setCellValue(String.valueOf(key.getDueDate()));
               cellLabel.setCellValue(key.getLabel());

            });

            sheet1.autoSizeColumn(0);
            sheet1.autoSizeColumn(1);
            sheet1.autoSizeColumn(2);
            sheet1.autoSizeColumn(3);
            sheet1.autoSizeColumn(4);
            sheet1.autoSizeColumn(5);

            FileOutputStream outputStream = new FileOutputStream(dest);
            workbook.write(outputStream);

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public Font createTitleFont(XSSFSheet sheet ){
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setFontName("Arial");
        font.setFontHeightInPoints((short)12);

        return font;
    }

    public void createHeadersCells(XSSFSheet sheet){
        XSSFRow row = sheet.createRow(1);
        XSSFCell cellNoTask = row.createCell(0);
        XSSFCell cellTitleTask = row.createCell(1);
        XSSFCell cellDescTask = row.createCell(2);
        XSSFCell cellStatusTask = row.createCell(3);
        XSSFCell cellDueDate = row.createCell(4);
        XSSFCell cellLabel = row.createCell(5);

        cellNoTask.setCellValue("No Task");
        cellTitleTask.setCellValue("Title");
        cellDescTask.setCellValue("Description");
        cellStatusTask.setCellValue("Status");
        cellDueDate.setCellValue("Due Date");
        cellLabel.setCellValue("Label");

    }
}
