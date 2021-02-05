package unit.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.in;
import static org.assertj.core.api.Assertions.linesOf;

public class CalculatorTest {

    private String[] values;
    private List<Integer> operands;
    private List<Operator> operators;

    @BeforeEach
    void setUp() {
        this.values = new String[] {"2", "+", "3", "*", "4", "/", "2"};
        this.operands = new ArrayList<>();
        this.operators = new ArrayList<>();
    }

    @Test
    @DisplayName("String의 replaceAll로 모든 공백을 제거할 수 있어야 한다.")
    void replaceAll_ShouldRemoveAllWhitespaceAmongCharArray() {
        String value1 = "2 + 3 * 4 / 2";
        String value2 = "2    + 3*4     /2";
        String sanitized1 = value1.replaceAll("\\s", "");
        String sanitized2 = value2.replaceAll("\\s", "");
        assertThat(sanitized1).isEqualTo("2+3*4/2");
        assertThat(sanitized2).isEqualTo("2+3*4/2");
        assertThat(sanitized1).isEqualTo(sanitized2);
    }

    @Test
    @DisplayName("빈 입력값일 경우 Runtime Exception을 발생시킬 수 있어야 한다.")
    void isEmpty_ShouldTriggerRuntimeExceptionWhenInputEmpty() {
        String input = "";
        assertThatThrownBy(() -> raiseRuntimeExceptionWhenInputEmpty(input))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("식을 입력해주세요");
    }

    private void raiseRuntimeExceptionWhenInputEmpty(String input) throws RuntimeException {
        if (input.isEmpty()) {
            throw new RuntimeException("식을 입력해주세요.");
        }
    }

    @Test
    @DisplayName("String의 split으로 ''을 기준으로 배열을 만들 수 있어야 한다.")
    void split_ShouldConvertStringToStringArray() {
        String value = "2+3*4/2";
        String[] converted = value.split("");
        assertThat(converted).isEqualTo(new String[]{"2", "+", "3", "*", "4", "/", "2"});
    }

    @Test
    @DisplayName("operator enum의 리스트를 만들 수 있어야 한다.")
    void makeOperatorListByEnum() {
        String[] operators = {"+", "-", "*", "/"};
        List<Operator> opEnumList = new ArrayList<>();
        for (String operator : operators) {
            switch (operator) {
                case "+":
                    opEnumList.add(Operator.ADD);
                case "-":
                    opEnumList.add(Operator.SUBTRACT);
                case "*":
                    opEnumList.add(Operator.MULTIPLY);
                case "/":
                    opEnumList.add(Operator.DIVIDE);
                default:
            }
        }

        assertThat(opEnumList.get(0)).isEqualTo(Operator.ADD);
        assertThat(opEnumList.get(1)).isEqualTo(Operator.SUBTRACT);
        assertThat(opEnumList.get(2)).isEqualTo(Operator.MULTIPLY);
        assertThat(opEnumList.get(3)).isEqualTo(Operator.DIVIDE);
    }

    @Test
    @DisplayName("입력값에서 operands를 분리할 수 있어야 한다.")
    void separateOperands() {
        List<Integer> operands = new ArrayList<>();
        for (String value : this.values) {
            try {
                operands.add(Integer.parseInt(value));
            } catch (NumberFormatException e) {

            }
        }

        assertThat(operands.get(0)).isEqualTo(2);
        assertThat(operands.get(1)).isEqualTo(3);
        assertThat(operands.get(2)).isEqualTo(4);
        assertThat(operands.get(3)).isEqualTo(2);
    }

    @Test
    @DisplayName("입력값에서 operators를 분리할 수 있어야 한다.")
    void separateOperators() {
        List<Operator> testOperators = new ArrayList<>();
        for (String value : this.values) {
            try {
                Integer.parseInt(value);
            } catch (NumberFormatException e) {
                switch (value) {
                    case "+":
                        testOperators.add(Operator.ADD);
                        continue;
                    case "-":
                        testOperators.add(Operator.SUBTRACT);
                        continue;
                    case "*":
                        testOperators.add(Operator.MULTIPLY);
                        continue;
                    case "/":
                        testOperators.add(Operator.DIVIDE);
                        continue;
                    default:
                        throw new RuntimeException("+-*/의 연산자만 입력가능합니다.");
                }
            }
        }

        assertThat(testOperators.get(0)).isEqualTo(Operator.ADD);
        assertThat(testOperators.get(1)).isEqualTo(Operator.MULTIPLY);
        assertThat(testOperators.get(2)).isEqualTo(Operator.DIVIDE);
    }

    @Test
    @DisplayName("Runtime Exception을 List로 저장할 수 있어야 한다.")
    void shouldSaveRuntimeExceptionContents() {
        List<RuntimeException> runtimeExceptions = new ArrayList<>();
        runtimeExceptions.add(new RuntimeException("runtime exception 1"));
        runtimeExceptions.add(new RuntimeException("runtime exception 2"));
        runtimeExceptions.add(new RuntimeException("runtime exception 3"));

        assertThat(runtimeExceptions.get(0).getMessage()).isEqualTo("runtime exception 1");
        assertThat(runtimeExceptions.get(1).getMessage()).isEqualTo("runtime exception 2");
        assertThat(runtimeExceptions.get(2).getMessage()).isEqualTo("runtime exception 3");
    }
}
