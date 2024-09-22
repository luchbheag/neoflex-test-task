package com.github.luchbheag.vacationpaycalculator.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface CalculatorService {
    public double getVacationPay(double averageSalary, int days);
    public double getVacationPayByPeriod(double averageSalary,
                                         LocalDate startDate,
                                         LocalDate endDate);
}
