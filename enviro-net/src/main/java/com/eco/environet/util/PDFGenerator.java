package com.eco.environet.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class PDFGenerator {
    public static void addTitleText(Document document, String titleText) {
        Paragraph paragraph = new Paragraph(titleText).setBold().setFontSize(24).setMarginTop(20);
        paragraph.setTextAlignment(TextAlignment.CENTER);
        document.add(paragraph);
    }

    public static void addTextParagraph(Document document, String text) {
        if (text != null && !text.isEmpty()) {
            Paragraph paragraph = new Paragraph(text).setBold().setFontSize(16).setMarginTop(20);
            paragraph.setTextAlignment(TextAlignment.LEFT);
            document.add(paragraph);
        }
    }

    public static void addTable(Document document, List<?> list, List<String> columnsList, Map<String, String> columnMappings) {
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
                    if (!columnName.equals("number")) {
                        Method method = obj.getClass().getMethod("get" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1));
                        Object value = method.invoke(obj);
                        table.addCell(value != null ? value.toString() : "");
                    } else {
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

    public static Resource generatePDF(List<?> items, String documentTitle, String text, List<String> columns, Map<String, String> columnMappings) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (
                PdfWriter writer = new PdfWriter(outputStream);
                PdfDocument pdfDocument = new PdfDocument(writer);
                Document document = new Document(pdfDocument)) {
            addTitleText(document, documentTitle);
            addTextParagraph(document, text);
            addTable(document, items, columns, columnMappings);
        }
        return new ByteArrayResource(outputStream.toByteArray());
    }
}
