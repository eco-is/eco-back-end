package com.eco.environet.finance.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
public class DateRange {
    private LocalDate startDate;
    private LocalDate endDate;
    public DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "DateRange[startDate=" + startDate +
                       ", endDate=" + endDate + "]";
    }

    public boolean isInFuture() {
        LocalDate currentDate = LocalDate.now();
        return startDate.isAfter(currentDate) && endDate.isAfter(currentDate);
    }
    public boolean isValid() {
        return startDate.isBefore(endDate);
    }

    public long getDurationInDays() {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}
