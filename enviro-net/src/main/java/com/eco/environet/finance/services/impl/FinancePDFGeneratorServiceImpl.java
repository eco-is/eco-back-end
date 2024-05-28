package com.eco.environet.finance.services.impl;

import com.eco.environet.finance.dto.FixedExpensesDto;
import com.eco.environet.finance.dto.RevenueDto;
import com.eco.environet.finance.services.FinancePDFGeneratorService;
import com.eco.environet.util.PDFGenerator;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FinancePDFGeneratorServiceImpl implements FinancePDFGeneratorService {
    @Value("${baseFrontUrl}")
    private String baseFrontUrl;

    private final Map<String, String> fixedExpensesColumnMappings = Map.of(
            "number", "#",
            "id", "Id",
            "type", "Type",
            "period", "Period",
            "amount", "Amount",
            "creator", "Created by",
            "createdOn", "Created on date",
            "description", "Description",
            "employee", "Employee",
            "overtimeHours", "Overtime hours"
    );
    @Override
    public Resource generateFixedExpensesPDF(List<FixedExpensesDto> expenses, String documentTitle, String text, List<String> columns) throws IOException {
        if (columns == null) {
            columns = List.of("number", "type", "description", "amount");
        }
        return PDFGenerator.generatePDF(expenses, documentTitle, text, columns, fixedExpensesColumnMappings);
    }

    private final Map<String, String> revenuesColumnMappings = Map.of(
            "number", "#",
            "id", "Id",
            "type", "Type",
            "amount", "Amount",
            "createdOn", "Created on date",
            "donator", "Donator",
            "project", "Project"
    );
    @Override
    public Resource generateRevenuePDF(List<RevenueDto> revenues, String documentTitle, String text, List<String> columns) throws IOException {
        if (columns == null) {
            columns = List.of("number", "type", "createdOn", "amount");
        }
        return PDFGenerator.generatePDF(revenues, documentTitle, text, columns, revenuesColumnMappings);
    }
}
