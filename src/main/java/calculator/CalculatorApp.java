package calculator;

import calculator.components.Operations;
import calculator.components.Pair;
import calculator.components.TxtFileReader;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import static calculator.components.Operations.APPLY;

public class CalculatorApp {
    private static final String path = "src/main/resources/instructions.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Result: " + calculate(path));
    }

    public static BigDecimal calculate(String path) throws IOException {
        List<Pair<Operations, BigDecimal>> operations = new TxtFileReader().getOperations(path);
        BigDecimal accumulator = getStartingPoint(operations);
        List<Pair<Operations, BigDecimal>> filteredOperations = operations.stream()
                .filter(op -> !op.left.equals(APPLY))
                .collect(Collectors.toList());

        return filteredOperations.stream()
                .reduce(Pair.of(APPLY, accumulator),
                        (val, acc) -> Pair.of(APPLY, apply(val.right, acc)))
                .right;
    }

    private static BigDecimal getStartingPoint(List<Pair<Operations, BigDecimal>> operations) {
        List<Pair<Operations, BigDecimal>> filteredOperations = operations.stream().filter(operation -> operation.left.equals(APPLY)).collect(Collectors.toList());
        return filteredOperations.get(filteredOperations.size() - 1).right;
    }

    private static BigDecimal apply(BigDecimal value, Pair<Operations, BigDecimal> operation) {
        switch (operation.left) {
            case ADD:
                return value.add(operation.right);
            case SUBTRACT:
                return value.subtract(operation.right);
            case DIVIDE:
                return value.divide(operation.right, 2, RoundingMode.HALF_UP);
            case MULTIPLY:
                return value.multiply(operation.right);
            default:
                throw new IllegalArgumentException("operation not supported: " + operation.left);
        }
    }
}
