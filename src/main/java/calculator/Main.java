package calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator(new Scanner(System.in));
        calculator.run();
    }
}
