package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    private final Scanner scanner;
    private final List<RuntimeException> inputExceptions;
    private List<Integer> operands;
    private List<Operator> operators;

    public Input(Scanner scanner) {
        this.scanner = scanner;
        this.inputExceptions = new ArrayList<>();
    }

    public void get() {
        getValues();
    }

    private void getValues() {
        String input = this.scanner.nextLine();
        sanitizeAndParseInput(input);
        compareOperandsSizeAndOperateSize();
        if (!this.inputExceptions.isEmpty()) {
            this.inputExceptions
                .forEach(exception -> System.out.println(exception.getMessage()));
        }
    }

    public boolean complete() {
        return this.inputExceptions.isEmpty();
    }

    public int getFirstOperand() {
        return this.operands.get(0);
    }

    public int getOperatorsSize() {
        return this.operators.size();
    }

    private void compareOperandsSizeAndOperateSize() {
        if (this.operands.size() - this.operators.size() != 1) {
            RuntimeExceptionSaver.save(this.inputExceptions,
                new RuntimeException("연산자는 숫자보다 하나가 적어야 합니다."));
        }
    }

    private void sanitizeAndParseInput(String input) {
        String validated = validatedInput(input);
        String sanitized = sanitizeWhiteSpace(validated);
        String[] splitByBlank = splitByBlank(sanitized);
        this.operands = separateOperands(splitByBlank);
        this.operators = separateOperators(splitByBlank);
    }

    private String sanitizeWhiteSpace(String inputValue) {
        return inputValue.replaceAll("\\s+", " ");
    }

    private String[] splitByBlank(String input) {
        return input.split(" ");
    }

    private List<Integer> separateOperands(String[] split) {
        List<Integer> newOperands = new ArrayList<>();
        for (String sp : split) {
            try {
                newOperands.add(Integer.parseInt(sp));
            } catch (NumberFormatException e) {
            }
        }
        return newOperands;
    }

    private List<Operator> separateOperators(String[] split) {
        List<Operator> newOperators = new ArrayList<>();
        for (String sp : split) {
            try {
                parseOperatorsByEnum(newOperators, sp);
            } catch (RuntimeException e) {
                RuntimeExceptionSaver.save(this.inputExceptions, new RuntimeException(e));
                break;
            }
        }
        return newOperators;
    }

    private void parseOperatorsByEnum(List<Operator> operators, String operatorMark)
        throws RuntimeException {
        try {
            Integer.parseInt(operatorMark);
        } catch (NumberFormatException e) {
            switch (operatorMark) {
                case "+":
                    operators.add(Operator.ADD);
                    return;
                case "-":
                    operators.add(Operator.SUBTRACT);
                    return;
                case "*":
                    operators.add(Operator.MULTIPLY);
                    return;
                case "/":
                    operators.add(Operator.DIVIDE);
                    return;
                default:
                    throw new RuntimeException("+-*/의 연산자만 입력가능합니다.");
            }
        }
    }

    private String validatedInput(String input) {
        if (input.isEmpty()) {
            RuntimeExceptionSaver.save(this.inputExceptions, new RuntimeException("식을 입력해주세요"));
        }
        return input;
    }

    public Operator getOperatorByIdx(int idx) {
        return this.operators.get(idx);
    }

    public int getOperandByIdx(int idx) {
        return this.operands.get(idx);
    }
}
