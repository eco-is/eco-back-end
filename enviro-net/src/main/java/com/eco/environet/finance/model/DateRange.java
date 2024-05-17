package com.eco.environet.finance.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
public class DateRange {
    private LocalDate startDate;
    private LocalDate endDate;
    public DateRange(){
        this.startDate = LocalDate.now();
        this.endDate = null;
    }
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
        if (endDate == null) {
            return true; // Goal is ongoing
        } else {
            return startDate.isBefore(endDate);
        }
    }

    public long getDurationInDays() {
        //return ChronoUnit.DAYS.between(startDate, endDate);
        LocalDate endDateToUse = endDate != null ? endDate : LocalDate.now();
        return ChronoUnit.DAYS.between(startDate, endDateToUse);
    }
}
