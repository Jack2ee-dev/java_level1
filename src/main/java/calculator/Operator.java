package calculator;

public enum Operator {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String mark;

    Operator(String mark) {
        this.mark = mark;
    }
}
