package calculator;

import java.util.ArrayList;
import java.util.List;

public class Operate {

    private Input input;
    private List<RuntimeException> calculateExceptions;
    private int result = 0;

    public Operate(Input input) {
        this.input = input;
        this.calculateExceptions = new ArrayList<>();
    }

    public int result() {
        return this.result;
    }

    public boolean complete() {
        return this.calculateExceptions.isEmpty();
    }

    public void calculate() {
        int result = this.input.getFirstOperand();
        for (int i = 0; i < this.input.getOperatorsSize(); i++) {
            try {
                result = operate(this.input.getOperatorByIdx(i), result, this.input.getOperandByIdx(i + 1));
            } catch (RuntimeException e) {
                RuntimeExceptionSaver.save(this.calculateExceptions, e);
            }
        }
        if (!this.calculateExceptions.isEmpty()) {
            this.calculateExceptions
                .forEach(exception -> System.out.println(exception.getMessage()));
        } else {
            this.result = result;
        }
    }

    private int operate(Operator operator, int num1, int num2) throws RuntimeException {
        if (operator == Operator.ADD) {
            return num1 + num2;
        }
        if (operator == Operator.SUBTRACT) {
            return num1 - num2;
        }
        if (operator == Operator.MULTIPLY) {
            return num1 * num2;
        }
        if (num2 == 0) {
            throw new RuntimeException("0으로 나눌 수 없습니다.");
        }
        return (int) (double) num1 / num2;
    }
}
