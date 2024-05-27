package com.eco.environet.finance.services.impl;

import com.eco.environet.finance.dto.FixedExpensesDto;
import com.eco.environet.finance.services.FinancePDFGeneratorService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.itextpdf.layout.properties.UnitValue;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class FinancePDFGeneratorServiceImpl implements FinancePDFGeneratorService {
    private void addTitleText(Document document, String titleText) {
        Paragraph paragraph = new Paragraph(titleText).setBold().setFontSize(24).setMarginTop(20);
        paragraph.setTextAlignment(TextAlignment.CENTER);
        document.add(paragraph);
    }
    private void addTextParagraph(Document document, String text) {
        Paragraph paragraph = new Paragraph(text).setBold().setFontSize(16).setMarginTop(20);
        paragraph.setTextAlignment(TextAlignment.LEFT);
        document.add(paragraph);
    }
    private void addTable(Document document, List<?> list, List<String> columnsList, Map<String, String> columnMappings) {
        Table table = new Table(columnsList.size());

        // header row
        for (String columnName : columnsList) {
            table.addHeaderCell(new Cell().add(new Paragraph(columnMappings.getOrDefault(columnName, columnName))));
        }

        // content rows
        int rowNum = 1;
        for (Object obj : list) {
            for (String columnName : columnsList) {
                try {
                    if (!columnName.equals("number")){
                        Method method = obj.getClass().getMethod("get" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1));
                        Object value = method.invoke(obj);
                        table.addCell(value != null ? value.toString() : "");
                    } else{
                        table.addCell(String.valueOf(rowNum++));
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                    table.addCell(""); // Add empty cell if method not found or inaccessible
                }
            }
        }
        table.setWidth(UnitValue.createPercentValue(100));

        document.add(table);
    }

    private final Map<String, String> columnMappings = Map.of(
            "number", "#",
            "id", "Id",
            "type", "Type",
            "period", "Period",
            "amount", "Amount",
            "creator", "Created by",
            "createdOn", "Created on date",
            "description", "Description",
            "employee", "Employee",
            "overtimeHours", "Overtime (h)"
            );
    public Resource generateFixedExpensesListPDF(List<FixedExpensesDto> expenses, String documentTitle, List<String> columns) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument)) {
            addTitleText(document, documentTitle);
            if (columns == null){
                columns = List.of("number", "type", "creator", "description", "amount");
            }
            addTable(document, expenses, columns, columnMappings);
        }
        return new ByteArrayResource(outputStream.toByteArray());
    }
}
