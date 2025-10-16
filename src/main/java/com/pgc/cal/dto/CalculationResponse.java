package com.pgc.cal.dto;

public record CalculationResponse(
        double num1,
        double num2,
        String operator,
        double result
) {
}