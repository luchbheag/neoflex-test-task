package com.github.luchbheag.vacationpaycalculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

@DisplayName("Unit-level testing for WorkingDayService")
public class WorkingDayServiceTest {

    private WorkingDayService workingDayService;

    @BeforeEach
    public void init() {
        workingDayService = new WorkingDayServiceImpl();
    }

    @Test
    public void shouldProperlyCountDaysWithNoHolidays() {
        LocalDate startDate = LocalDate.of(2024, 3, 2);
        LocalDate endDate = LocalDate.of(2024, 3, 7);
        assertEquals(6, workingDayService.getDays(startDate, endDate));
    }

    @Test
    public void shouldProperlyCountDaysWithHolidays() {
        LocalDate startDate = LocalDate.of(2024, 3, 2);
        LocalDate endDate = LocalDate.of(2024, 3, 9);
        assertEquals(7, workingDayService.getDays(startDate, endDate));
    }

    @Test
    public void shouldThrowExceptionIfStartDateIsAfterEndDate() {
        LocalDate startDate = LocalDate.of(2024, 3, 2);
        LocalDate endDate = LocalDate.of(2024, 3, 1);
        assertThrows(IllegalArgumentException.class, () -> workingDayService.getDays(startDate, endDate));
    }

    @Test
    public void shouldThrowExceptionIfPeriodIsMoreThanTwentyEightDays() {
        LocalDate startDate = LocalDate.of(2024, 3, 2);
        LocalDate endDate = LocalDate.of(2024, 4, 7);
        assertThrows(IllegalArgumentException.class, () -> workingDayService.getDays(startDate, endDate));
    }

}
