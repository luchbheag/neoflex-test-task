package com.github.luchbheag.vacationpaycalculator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldProperlyCalculateVacationPay() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/calculate")
                .param("averageSalary", String.valueOf(351600))
                .param("vacationDays", String.valueOf(10)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(10000.00)));
    }

    @Test
    public void shouldProperlyCalculateVacationPayByDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/calculate")
                        .param("averageSalary", String.valueOf(351600))
                        .param("startDate", "2024-02-02")
                        .param("endDate", "2024-02-11"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(10000.00)));
    }
}
