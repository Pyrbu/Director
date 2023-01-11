package lol.pyr.extendedcommands.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * A class containing utility methods for working with the {@link Stack} data structure
 */
public class StackUtil {
    public static <T> Stack<T> fromArray(T[] array) {
        return fromArray(array, true);
    }

    public static <T> Stack<T> fromArray(T[] array, boolean reversed) {
        List<T> list = new ArrayList<>(List.of(array));
        if (reversed) Collections.reverse(list);

        Stack<T> stack = new Stack<>();
        stack.addAll(list);
        return stack;
    }

    @SuppressWarnings("unchecked")
    public static <T> Stack<T> clone(Stack<T> stack) {
        return (Stack<T>) stack.clone();
    }
}
