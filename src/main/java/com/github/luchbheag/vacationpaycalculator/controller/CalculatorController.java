package com.github.luchbheag.vacationpaycalculator.controller;


import com.github.luchbheag.vacationpaycalculator.service.CalculatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping
@Validated
public class CalculatorController {
    private final CalculatorServiceImpl calculatorService;

    @Autowired
    public CalculatorController(final CalculatorServiceImpl calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping(value = "/calculate", params = {"averageSalary", "vacationDays"})
    public String getVacationPayByAmountOfDays(@RequestParam double averageSalary,
                              @RequestParam int vacationDays) {
        return String.valueOf(calculatorService.getVacationPay(averageSalary, vacationDays));
    }

    @GetMapping(value = "/calculate", params = {"averageSalary", "startDate", "endDate"})
    public String getVacationPayByPeriodOfTime(@RequestParam double averageSalary,
                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return String.valueOf(calculatorService.getVacationPayByPeriod(averageSalary, startDate, endDate));
    }
}
