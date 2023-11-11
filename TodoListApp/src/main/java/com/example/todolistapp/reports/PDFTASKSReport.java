package com.example.todolistapp.reports;

import com.example.todolistapp.models.Task;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import db.dao.TaskDao;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PDFTASKSReport {
    TaskDao taskDao = new TaskDao();

    public void createPDF( String dest ) throws IOException {
        PdfWriter writer = new PdfWriter(dest);

        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20,20,20,20);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        Table table = new Table(UnitValue.createPercentArray( new float[]{2,4,6,3,3})).useAllAvailableWidth();

        addTableHeaders(table, bold);

        for (Task task : taskDao.findAll()) {
            addTableRow( table, task, font );
        }

        Paragraph title = new Paragraph("Task list");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(bold);
        title.setFontSize(30);
        title.setFontColor(ColorConstants.BLUE);
        document.add(title);
        document.add(table);

        document.close();
    }

    public void addTableHeaders( Table table, PdfFont font ){
        table.addHeaderCell(new Cell().add(new Paragraph("No.Task").setFont(font)));
        table.addHeaderCell(new Cell().add(new Paragraph("Title").setFont(font)));
        table.addHeaderCell(new Cell().add(new Paragraph("Description").setFont(font)));
        table.addHeaderCell(new Cell().add(new Paragraph("Status").setFont(font)));
        table.addHeaderCell(new Cell().add(new Paragraph("Due date").setFont(font)));
    }

    private void addTableRow(Table table, Task task, PdfFont font){
        table.addCell(new Cell().add(new Paragraph(String.valueOf(task.getId())).setFont(font)));
        table.addCell(new Cell().add(new Paragraph(task.getName()).setFont(font)));
        table.addCell(new Cell().add(new Paragraph(task.getDescription()).setFont(font)));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(task.getStatus())).setFont(font)));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(task.getDueDate())).setFont(font)));
    }
}
