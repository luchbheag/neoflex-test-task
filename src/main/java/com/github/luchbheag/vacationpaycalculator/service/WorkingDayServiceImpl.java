package com.github.luchbheag.vacationpaycalculator.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class WorkingDayServiceImpl implements WorkingDayService {
    static List<LocalDate> holidays = List.of(
        LocalDate.of(2024, 1, 1),
        LocalDate.of(2024, 1, 2),
        LocalDate.of(2024, 1, 3),
        LocalDate.of(2024, 1, 4),
        LocalDate.of(2024, 1, 5),
        LocalDate.of(2024, 1, 6),
        LocalDate.of(2024, 1, 7),
        LocalDate.of(2024, 1, 8),
        LocalDate.of(2024, 2, 23),
        LocalDate.of(2024, 3, 8),
        LocalDate.of(2024, 5, 1),
        LocalDate.of(2024, 5, 9),
        LocalDate.of(2024, 6, 12),
        LocalDate.of(2024, 11, 4)
    );

    @Override
    public int getDays(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        if (startDate.getYear() != 2024 || endDate.getYear() != 2024) {
            throw new IllegalArgumentException("Both date should be in 2024");
        }
        Period period = Period.between(startDate, endDate);
        int days = period.getDays() + 1;
        if (period.getYears() != 0 || period.getMonths() != 0 || days > 28) {
            throw new IllegalArgumentException("The vacation should last 28 or less days");
        }
        for (LocalDate ld : holidays) {
            if (ld.isAfter(startDate) && ld.isBefore(endDate) ||
                ld.isEqual(startDate) || ld.isEqual(endDate)) {
                days--;
            }
        }
        return days;
    }
}
