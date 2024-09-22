package com.github.luchbheag.vacationpaycalculator.service;

import java.time.LocalDate;

public interface WorkingDayService {
    public int getDays(LocalDate startDate, LocalDate endDate);
}
