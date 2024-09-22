package com.github.luchbheag.vacationpaycalculator.service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@DisplayName("Unit-level testing for CalculatorService")
public class CalculatorServiceTest {
    private WorkingDayService workingDayService;
    private CalculatorService calculatorService;

    @BeforeEach
    public void init() {
        workingDayService = Mockito.mock(WorkingDayService.class);
        calculatorService = new CalculatorServiceImpl(workingDayService);
    }

    @Test
    public void shouldProperlyCalculateVacationPayByAverageSalary() {
        double averageSalary = 100000;
        int days = 10;
        double expectedVacationPay = BigDecimal.valueOf(averageSalary * days).divide(BigDecimal.valueOf(29.3 * 12), 2, RoundingMode.HALF_UP).doubleValue();
        assertEquals(expectedVacationPay, calculatorService.getVacationPay(averageSalary, days));
    }

    @Test
    public void shouldProperlyCalculateVacationPayByAverageSalaryAndDate() {
        LocalDate startDate = LocalDate.of(2024, 2, 5);
        LocalDate endDate = LocalDate.of(2024, 2, 14);
        int days = 10;
        Mockito.when(workingDayService.getDays(startDate, endDate)).thenReturn(days);
        double averageSalary = 100000;
        double expectedVacationPay = BigDecimal.valueOf(averageSalary * days).divide(BigDecimal.valueOf(12 * 29.3), 2, RoundingMode.HALF_UP).doubleValue();
        assertEquals(expectedVacationPay, calculatorService.getVacationPay(averageSalary, days));
    }

    @Test
    public void shouldThrowExceptionIfSalaryIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> calculatorService.getVacationPay(-1, 1));
    }

    @Test
    public void shouldThrowExceptionIfDaysIsGreaterThanTwentyEight() {
        assertThrows(IllegalArgumentException.class, () -> calculatorService.getVacationPay(100, 29));
    }
}
