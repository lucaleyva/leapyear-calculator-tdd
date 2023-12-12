# Leap Year Calculator Kata Instructions
#### • Write a function that returns true or false depending on whether its input integer is a leap year or not.
#### • A leap year is defined as one that is divisible by 4, but is not otherwise divisible by 100 unless it is also divisible by 400.
#### • For example, 2001 is a typical common year and 1996 is a typical leap year, whereas 1900 is an atypical common year and 2000 is an atypical leap year.
<br>
This project is a simple Java application that calculates if a given year is a leap year or not. It follows the TDD (Test-Driven Development) approach, where tests are written before the actual implementation.
TDD Process
<br>
The TDD process followed in this project is as follows:

1. Write a failing test: Before writing any implementation code, a test case is written that fails because the required functionality is not yet implemented.
2. Write minimal code to pass the test: The minimal amount of code is written to make the test pass.
3. Refactor: The code is then refactored to meet quality standards while ensuring that the test still passes.


This cycle is repeated for each new feature or functionality.

## Project Structure
- src/main/java/LeapYear.java: This is the main class that contains the logic for determining if a year is a leap year.
- src/test/java/LeapYearTest.java: This class contains the unit tests for the LeapYear class.
- pom.xml: This is the Maven Project Object Model (POM) file. It contains information about the project and configuration details used by Maven to build the project.

## Installation

To install the dependencies, you need to have Maven installed. Once you have Maven, you can install the dependencies using the following command:
```mvn install```

Running the Tests

To run the tests, use the following command:
```mvn test```

## TDD Steps
Following these steps will guide you through the Test-Driven Development (TDD) process for the leap year kata.
### Step 1: Write a failing test for a "common year" that is not divisible by 4.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LeapYearTest {

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
}
```

### Step 2: Write the production code to pass the failing test.

```java
public class LeapYear {

    public static boolean isLeapYear(int year) {
        return false; // always return false for now
    }
}
```

### Step 3: Write a failing test for a "common leap year" that is divisible by 4 but not by 100.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeapYearTest {

    // Previous test omitted for brevity

    @DisplayName("Divisible by 4 NOT by 100")
    @Test
    public void itValidatesTypicalLeapYearIsDivisibleBy4_NotBy100() {
        int year = 1996;
        boolean expected = true;
        boolean result = LeapYearCalculator.isLeapYear(year);
        assertEquals(expected, result, () -> expected + " this is not a Typical Leap Year");
    }
}
```

### Step 4: Update the production code to pass the new failing test.

```java
public class LeapYear {

    public static boolean isLeapYear(int year) {
        return year % 4 == 0;
    }
}
```

### Step 5: Write a failing test for an "atypical common year" that is divisible by 100 but not by 400. An atypical common year should return false as it is not a Leap Year.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LeapYearTest {

    // Previous tests omitted for brevity

    @DisplayName("Divisible by 100 but NOT by 400")
    @Test
    public void itReturnsFalseIfYearIsDivisibleBy100_NotBy400(){
        int year = 1900;
        boolean expected = false;
        boolean result = LeapYearCalculator.isLeapYear(year);
        assertEquals(expected, result, ()-> expected + " is not an atypical common year.");
    }
}
```

### Step 6: Update the production code to pass the new failing test.

```java
public class LeapYear {

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0);
    }
}
```

### Step 7: Write a failing test for an "atypical leap year" that is divisible by both 100 and 400.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeapYearTest {

    // Previous tests omitted for brevity

    @DisplayName("Divisible by 4 AND 400")
    @Test
    public void itValidatesAtypicalLeapYearIsDivisibleBy4And400(){
        int year = 2000;
        boolean expected = true;
        boolean result = LeapYearCalculator.isLeapYear(year);
        assertEquals(expected, result, ()-> expected + " is not an atypical leap year");
    }
}
```

### Step 8: Update the production code to pass the new failing test.

```java
public class LeapYear {

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }
}
```

### Step 9: Refactor to a composed method for readability.
#### Step 9a: Extract out the "common leap year" logic (divisible by 4)

```java
public class LeapYear {

    public static boolean isLeapYear(int year) {
        return isCommonLeapYear(year) && (year % 100 != 0 || year % 400 == 0);
    }

    private static boolean isCommonLeapYear(int year) {
        return year % 4 == 0;
    }
}
```
#### Step 9b: Extract out the "is not an atypical common year" logic (NOT divisible by 100)

```java
public class LeapYear {

    public static boolean isLeapYear(int year) {
        return isCommonLeapYear(year) && (isNotAtypicalCommonYear(year) || year % 400 == 0);
    }

    private static boolean isNotAtypicalCommonYear(int year) {
        return year % 100 != 0;
    }

    private static boolean isCommonLeapYear(int year) {
        return year % 4 == 0;
    }
}
```

#### Step 9c: Extract out the "atypical leap year" logic (divisible by 400)

```java
public class LeapYear {

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
```
