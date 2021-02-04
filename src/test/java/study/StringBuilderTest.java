package study;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringBuilderTest {

    @Test
    public void append() {
        assertEquals(createMessage(14), "코드스쿼드 백엔드 수강생은? 14 명이다.");
    }

    private String createMessage(int numberOfClass) {
        StringBuilder sb = new StringBuilder();
        sb.append("코드스쿼드 백엔드 수강생은? ")
            .append(numberOfClass)
            .append(" 명이다.");
        return sb.toString();
    }
}
