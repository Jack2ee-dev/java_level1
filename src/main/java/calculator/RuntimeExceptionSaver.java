package calculator;

import java.util.List;

public class RuntimeExceptionSaver {

    public static void save(List<RuntimeException> exceptions, RuntimeException e) {
        exceptions.add(e);
    }
}
