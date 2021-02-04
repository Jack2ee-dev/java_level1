package study;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListTest {

    @Test
    public void arrayList() throws Exception {
        List<String> values = new ArrayList<>();
        values.add("first");
        values.add("second");

        assertTrue(add(values, "third"));
        assertEquals(values.size(), 3);
        assertEquals(values.get(0), "first");
        assertTrue(values.contains("first"));
        assertEquals(deleteFirst(values), "first");
        assertEquals(values.size(), 2);

        values.forEach(System.out::println);
    }

    private String deleteFirst(List<String> arrayList) {
        return arrayList.remove(0);
    }

    private boolean add(List<String> arrayList, String element) {
        return arrayList.add(element);
    }

    @Test
    public void linkedList() throws Exception {
        List<String> values = new LinkedList<>();
        values.add("first");
        values.add("second");

        assertTrue(add(values, "third"));
        assertEquals(values.size(), 3);
        assertEquals(values.get(0), "first");
        assertTrue(values.contains("first"));
        assertEquals(deleteFirst(values), "first");
        assertEquals(values.size(), 2);

        values.forEach(System.out::println);
    }
}
