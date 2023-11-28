package com.mycompany;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeapYearCalculatorTest {
    @DisplayName("NOT divisible by 4")
    @Test
    public void itValidatesCommonYearIsNotDivisibleBy4() {
        //Arrange
        int year = 2001;
        boolean expected = false;
        //Act
        boolean result = LeapYearCalculator.isLeapYear(year);
        //Assert
        assertEquals(expected, result, ()-> year + " is not a Common Year");
    }

    @DisplayName("Divisible by 4 NOT by 100")
    @Test
    public void itValidatesTypicalLeapYearIsDivisibleBy4_NotBy100() {
        int year = 1996;
        boolean expected = true;
        boolean result = LeapYearCalculator.isLeapYear(year);
        assertEquals(expected, result, () -> expected + " this is not a Typical Leap Year");
    }
    @DisplayName("Divisible by 100 but NOT by 400")
    @Test
    public void itReturnsFalseIfYearIsDivisibleBy100_NotBy400(){
        int year = 1900;
        boolean expected = false;
        boolean result = LeapYearCalculator.isLeapYear(year);
        assertEquals(expected, result, ()-> expected + " is not an atypical common year.");
    }

    @DisplayName("Divisible by 4 AND 400")
    @Test
    public void itValidatesAtypicalLeapYearIsDivisibleBy4And400(){
        int year = 2000;
        boolean expected = true;
        boolean result = LeapYearCalculator.isLeapYear(year);
        assertEquals(expected, result, ()-> expected + " is not an atypical leap year");
    }
}