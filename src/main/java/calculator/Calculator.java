package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    private Scanner scanner;
    private Input input;
    private Operate operate;
    private int result;

    public Calculator(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            setUp();
            this.input.get();
            if (!this.input.complete()) {
                continue;
            }
            this.operate.calculate();
            if (this.operate.complete()) {
                break;
            }
        }
        printResult();
    }

    private void printResult() {
        System.out.println(this.operate.result());
    }

    private void setUp() {
        this.input = new Input(this.scanner);
        this.operate = new Operate(this.input);
    }

}
