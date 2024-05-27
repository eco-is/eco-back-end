package com.eco.environet.finance.services;

import com.eco.environet.finance.dto.FixedExpensesDto;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface FinancePDFGeneratorService {

    public Resource generateFixedExpensesListPDF(List<FixedExpensesDto> expenses, String documentTitle, List<String> columns) throws IOException;
}
