package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringTest {

    @Test
    @DisplayName("1과 2로 잘 분리되는지")
    public void splitOneAndTwo() {
        String[] split = "1,2".split(",");
        assertThat(split).contains("1", "2");
    }

    @Test
    @DisplayName("1만 ,로 잘 분리되는지")
    public void splitOnlyOne() {
        String[] split = "1".split(",");
        assertThat(split).containsExactly("1");
    }

    @Test
    @DisplayName("(1,2)로 주어졌을 때 substring()으로 ()를 제거하고 1,2를 반환하는지")
    public void substring() {
        String input = "(1,2)";
        input = input.substring(1, input.length() - 1);
        assertThat(input).isEqualTo("1,2");
    }

    @Test
    @DisplayName("chartAt() 테스트")
    public void charAt() {
        String abc = "abc";
        assertThatThrownBy(() ->
            assertThat(abc.charAt(0))
                .isEqualTo('a'))
            .isInstanceOf(IndexOutOfBoundsException.class)
            .hasMessageContaining("Index: \\d+, Size: \\d+");
        assertThatThrownBy(() ->
            assertThat(abc.charAt(1))
                .isEqualTo('b'))
            .isInstanceOf(IndexOutOfBoundsException.class)
            .hasMessageContaining("Index: \\d+, Size: \\d+");
        assertThatThrownBy(() ->
            assertThat(abc.charAt(2))
                .isEqualTo('c'))
            .isInstanceOf(IndexOutOfBoundsException.class)
            .hasMessageContaining("Index: \\d+, Size: \\d+");
        assertThatThrownBy(() -> abc.charAt(4))
            .isInstanceOf(IndexOutOfBoundsException.class)
            .hasMessageContaining("Index: \\d+, Size: \\d+");
    }
}
