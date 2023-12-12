# Setting Up a New Maven Project in IntelliJ IDEA

## Install IntelliJ IDEA
Download and install IntelliJ IDEA (Community Edition) from [JetBrains](https://www.jetbrains.com/idea/download/).

## Create a New Project
Open IntelliJ IDEA and on the welcome screen, select "New Project".

## Select Project SDK
Choose Java from the list on the left and set the "Project SDK" to Java 21.

## Configure Project
Select "Maven" as the project type and check "Create from archetype", choosing `maven-archetype-quickstart`. Click "Next".

## Project Details
Enter your GroupId (e.g., `com.example`) and ArtifactId (e.g., `LeapYearCalculator`). Choose a project name and location. Click "Finish".

## Update `pom.xml` for JUnit 5
Add the JUnit 5 dependency to your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.7.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

# TDD Steps for Leap Year Calculator Kata

## Step 1: Write a Failing Test for a "Common Year"
Create `LeapYearTest.java` in `src/test/java` and write your first failing test using the Given-When-Then format:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LeapYearTest {

    @Test
    public void itValidatesCommonYearIsNotDivisibleBy4() {
        // Given: a year
        int year = 2001;
        
        // When: checking if it's a leap year
        boolean result = LeapYear.isLeapYear(year);
        
        // Then: it should not be a leap year
        assertFalse(result, () -> year + " should not be a leap year");
    }
}
```

## Step 2: Write the Production Code
Create `LeapYear.java` in `src/main/java`:

```java
public class LeapYear {

    public static boolean isLeapYear(int year) {
        return false;
    }
}
```

## Step 3: Failing Test for a "Typical Leap Year"
Add another test method:

```java
import static org.junit.jupiter.api.Assertions.assertTrue;

// ...

@Test
public void itValidatesTypicalLeapYearIsDivisibleBy4_NotBy100() {
    int year = 1996;
    boolean result = LeapYear.isLeapYear(year);
    assertTrue(result, () -> year + " should be a typical leap year");
}
```

## Step 4: Update Production Code
Modify `LeapYear.java`:

```java
public class LeapYear {

    public static boolean isLeapYear(int year) {
        return year % 4 == 0;
    }
}
```

## Step 5: Failing Test for an "Atypical Common Year"
Add a new test method:

```java
@Test
public void itReturnsFalseIfYearIsDivisibleBy100_NotBy400(){
    int year = 1900;
    boolean result = LeapYear.isLeapYear(year);
    assertFalse(result, () -> year + " should be an atypical common year");
}
```

## Step 6: Update Production Code
Modify `LeapYear.java`:

```java
public class LeapYear {

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0);
    }
}
```

## Step 7: Failing Test for an "Atypical Leap Year"
Add another test method:

```java
@Test
public void itValidatesAtypicalLeapYearIsDivisibleBy4And400(){
    int year = 2000;
    boolean result = LeapYear.isLeapYear(year);
    assertTrue(result, () -> year + " should be an atypical leap year");
}
```

## Step 8: Update Production Code
Update `LeapYear.java`:

```java
public class LeapYear {

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }
}
```

## Refactor to a Composed Method for Readability
### Step 9: Extract "Common Leap Year" Logic
Refactor `LeapYear.java`:

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

### Step 10: Extract "Not Atypical Common Year" Logic
Refactor `LeapYear.java`:

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

### Step 11: Extract "Atypical Leap Year" Logic
Refactor `LeapYear.java`:

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

## Running Tests and Development
### Step 12: Run Tests
Right-click on `LeapYearTest.java` and select "Run 'LeapYearTest'". Follow the TDD cycle: write tests, make them fail, write code to pass, and refactor.
