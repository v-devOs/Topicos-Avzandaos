package com.example.employeesreports.reports;


import com.example.employeesreports.DAO.EmployeesDao;
import com.example.employeesreports.models.Employees;
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

import java.io.IOException;

/**
 * Simple table example.
 */
public class EmployeesReport {

    EmployeesDao employeesDao = new EmployeesDao();
    //Dao<Employees> employeesDao = new EmployeesDao();

    public void createPdf(String dest) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Table table = new Table(UnitValue.createPercentArray(new float[]{2,4,4,4,2,4}))
                .useAllAvailableWidth();

        addTableHeaders(table, bold);

        for(Employees employee : employeesDao.findAll()){
            addTableRow(table, employee, font);
        }


        Paragraph title = new Paragraph("Employees List");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(bold);
        title.setFontSize(30);
        title.setFontColor(ColorConstants.BLUE);
        document.add(title);
        document.add(table);

        //Close document
        document.close();
    }

    private void addTableHeaders(Table table, PdfFont font) {
        table.addHeaderCell(new Cell().add(new Paragraph("EMP. NO.").setFont(font)));
        table.addHeaderCell(new Cell().add(new Paragraph("FIRST NAME").setFont(font)));
        table.addHeaderCell(new Cell().add(new Paragraph("LAST NAME").setFont(font)));
        table.addHeaderCell(new Cell().add(new Paragraph("BIRTH DATE").setFont(font)));
        table.addHeaderCell(new Cell().add(new Paragraph("GENDER").setFont(font)));
        table.addHeaderCell(new Cell().add(new Paragraph("HIRE DATE").setFont(font)));
    }

    private void addTableRow(Table table, Employees employee, PdfFont font) {
        table.addCell(new Cell().add(new Paragraph(String.valueOf(employee.getEmp_no())).setFont(font)));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(employee.getFirst_name())).setFont(font)));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(employee.getLast_name())).setFont(font)));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(employee.getBirth_date())).setFont(font)));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(employee.getGender())).setFont(font)));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(employee.getHire_date())).setFont(font)));
    }
}
