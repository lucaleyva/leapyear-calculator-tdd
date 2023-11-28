package com.mycompany;
// refactor to get input from user
public class LeapYearCalculator {

    public static boolean isLeapYear(int year) {
        return isCommonLeapYear(year) && (isNotAtypicalCommonYear(year) || isAtypicalLeapYear(year));
    }

    private static boolean isAtypicalLeapYear(int year) {
        return year % 400 == 0;
    }

    private static boolean isNotAtypicalCommonYear(int year) {
        return year % 100 != 0;
    }

    private static boolean isCommonLeapYear(int year) {
        return year % 4 == 0;
    }
}