package unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    private String[] values;
    private List<String> operators;
    private List<Integer> operands;

    @BeforeEach
    public void setUp() {
        this.values = "2 + 3 * 4 / 2".split(" ");
        this.operators = new ArrayList<>();
        this.operands = new ArrayList<>();
    }

    @Test
    @DisplayName("입력받은 문자열이 공백을 기준으로 문자열 array에 파싱해야한다.")
    public void split_ShouldParseToStringArray() {
        assertThat(this.values[0]).isEqualTo("2");
        assertThat(this.values[1]).isEqualTo("+");
        assertThat(this.values[2]).isEqualTo("3");
        assertThat(this.values[3]).isEqualTo("*");
        assertThat(this.values[4]).isEqualTo("4");
        assertThat(this.values[5]).isEqualTo("/");
        assertThat(this.values[6]).isEqualTo("2");
    }

    @Test
    @DisplayName("문자열에서 operator와 operand를 분리해야 한다.")
    public void parseToInt_ShouldSeparateOperandsFromOperators() {
        for (String val : this.values) {
            try {
                this.operands.add(Integer.parseInt(val));
            } catch (NumberFormatException e) {
                this.operators.add(val);
            }
        }

        assertThat(this.operands.size()).isEqualTo(4);
        assertThat(this.operands.get(0)).isEqualTo(2);
        assertThat(this.operands.get(1)).isEqualTo(3);
        assertThat(this.operands.get(2)).isEqualTo(4);
        assertThat(this.operands.get(3)).isEqualTo(2);

        assertThat(this.operators.size()).isEqualTo(3);
        assertThat(this.operators.get(0)).isEqualTo("+");
        assertThat(this.operators.get(1)).isEqualTo("*");
        assertThat(this.operators.get(2)).isEqualTo("/");
    }

    @Test
    @DisplayName("operands의 size()는 operator의 size()보다 1이 커야한다.")
    public void size_ShouldOperandsSizeBeLargerThanOperatorsSizeByOne() {
        assertThat(this.operands.size() - this.operators.size()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource(value = {"+:true", "-:true", "*:true", "/:true", "(:false", "):false"}, delimiter = ':')
    @DisplayName("operands는 +-*/만 가능하다.")
    public void operands_ShouldBeOneOfFourOperands(String input, String expected) {
        String[] possibleOperands = {"+", "-", "*", "/"};
        boolean result = Arrays.asList(possibleOperands).contains(input);
        assertThat(result).isEqualTo(Boolean.parseBoolean(expected));
    }
}
