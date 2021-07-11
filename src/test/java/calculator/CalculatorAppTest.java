package calculator;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorAppTest {
    @Test
    void shouldAddWhenCalculate() throws IOException {
        // given
        String path = "src/test/resources/AddOperationScenario.txt";
        BigDecimal expected = new BigDecimal(30);
        // when
        // then
        assertEquals(expected, CalculatorApp.calculate(path));
    }

    @Test
    void shouldSubtractWhenCalculate() throws IOException {
        // given
        String path = "src/test/resources/SubtractOperationScenario.txt";
        BigDecimal expected = new BigDecimal(8);
        // when
        // then
        assertEquals(expected, CalculatorApp.calculate(path));
    }

    @Test
    void shouldMultiplyWhenCalculate() throws IOException {
        // given
        String path = "src/test/resources/MultiplyOperationScenario.txt";
        BigDecimal expected = new BigDecimal("42.0");
        // when
        // then
        assertEquals(expected, CalculatorApp.calculate(path));
    }

    @Test
    void shouldDivideWhenCalculate() throws IOException {
        // given
        String path = "src/test/resources/DivideOperationScenario.txt";
        BigDecimal expected = new BigDecimal("6.00");
        // when
        // then
        assertEquals(expected, CalculatorApp.calculate(path));
    }

    @Test
    void shouldFollowAllInstructionsWhenCalculate() throws IOException {
        // given
        String path = "src/test/resources/InstructionsScenario.txt";
        BigDecimal expected = new BigDecimal("3.33");
        // when
        // then
        assertEquals(expected, CalculatorApp.calculate(path));
    }

    @Test
    void shouldNotFollowWrongOperationWhenCalculate() throws IOException {
        // given
        String path = "src/test/resources/WrongOperationScenario.txt";
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> CalculatorApp.calculate(path));
    }
}