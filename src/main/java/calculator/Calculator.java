package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    private static final String[] POSSIBLE_OPERATORS = {"+", "-", "*", "/"};

    private final Scanner scanner;
    private String[] values;
    private boolean validated = false;
    private List<String> operators;
    private List<Integer> operands;

    public Calculator(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        do {
            setUp();
            getInput();
            separateOperandsAndOperators();
            validate();
        } while (!this.validated);
        calculate();
    }

    private void setUp() {
        this.operators = new ArrayList<>();
        this.operands = new ArrayList<>();
    }

    private void getInput() {
        this.values = this.scanner.nextLine().split(" ");
    }

    private void separateOperandsAndOperators() {
        for (String val : this.values) {
            parse(val);
        }
    }

    private void parse(String value) {
        if (parsableToInt(value)) {
            this.operands.add(Integer.parseInt(value));
        } else {
            this.operators.add(value);
        }
    }

    private boolean parsableToInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void validate() {
        if (isValidatedSize() && isValidatedOperators()) {
            this.validated = true;
        }
    }

    private boolean isValidatedSize() {
        try {
            throwErrorIfOperandsSizeIsNotLargerThanOperatorsSizeByOne();
            return true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean isValidatedOperators() {
        try {
            throwErrorIfNotContained();
            return true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void throwErrorIfOperandsSizeIsNotLargerThanOperatorsSizeByOne()
        throws RuntimeException {
        if (this.operands.size() - this.operators.size() != 1) {
            throw new RuntimeException("연산자의 수는 숫자의 수보다 1이 작아야 합니다.");
        }
    }

    private void throwErrorIfNotContained() throws RuntimeException {
        for (String operator : this.operators) {
            if (!Arrays.asList(POSSIBLE_OPERATORS).contains(operator)) {
                throw new RuntimeException("연산자는 +-*/만 가능합니다.");
            }
        }
    }

    private void calculate() {
        double result = this.operands.get(0);
        for (int i = 0; i < this.operators.size(); i++) {
            String operator = this.operators.get(i);
            int operand = this.operands.get(i + 1);
            if (operator.equals("+")) {
                result += operand;
            }
            if (operator.equals("-")) {
                result -= operand;
            }
            if (operator.equals("*")) {
                result *= operand;
            }
            if (operator.equals("/")) {
                result /= operand;
            }
        }
        System.out.println(result);
    }
}
