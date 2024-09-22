package com.github.luchbheag.vacationpaycalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    private final WorkingDayService workingDayService;
    private BigDecimal daysForAverage = BigDecimal.valueOf(29.3 * 12);

    @Autowired
    public CalculatorServiceImpl(WorkingDayService workingDayService) {
        this.workingDayService = workingDayService;
    }

    public double getVacationPay(double averageSalary, int days) {
        if (averageSalary <= 0) {
            throw new IllegalArgumentException("Average salary must be greater than 0");
        }
        if (days < 1 || days > 28) {
            throw new IllegalArgumentException("Number of vacation days must be between 1 and 28");
        }
        return BigDecimal.valueOf(averageSalary).multiply(BigDecimal.valueOf(days)).divide(daysForAverage, 2, RoundingMode.HALF_UP).doubleValue();
    }

    public double getVacationPayByPeriod(double averageSalary,
                                         LocalDate startDate,
                                         LocalDate endDate) {
        if (averageSalary <= 0) {
            throw new IllegalArgumentException("Average salary must be greater than 0");
        }
        int days = workingDayService.getDays(startDate, endDate);
        return BigDecimal.valueOf(averageSalary).multiply(BigDecimal.valueOf(days)).divide(daysForAverage, 2, RoundingMode.HALF_UP).doubleValue();
    }
}
