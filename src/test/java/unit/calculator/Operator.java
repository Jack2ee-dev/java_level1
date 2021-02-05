package unit.calculator;

public enum Operator {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String mark;

    Operator(String mark) {
        this.mark = mark;
    }

    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int subtract(int num1, int num2) {
        return num1 - num2;
    }

    public int multiply(int num1, int num2) {
        return num1 * num2;
    }

    public int divide(int num1, int num2) throws ArithmeticException {
        if (num2 == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return (int) (double) num1 / num2;
    }
}
