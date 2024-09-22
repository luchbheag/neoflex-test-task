package com.github.luchbheag.vacationpaycalculator;

import com.github.luchbheag.vacationpaycalculator.controller.CalculatorController;
import com.github.luchbheag.vacationpaycalculator.service.CalculatorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.github.luchbheag.vacationpaycalculator.controller",
        "com.github.luchbheag.vacationpaycalculator.service"
})
public class VacationPayCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacationPayCalculatorApplication.class, args);
    }

}
