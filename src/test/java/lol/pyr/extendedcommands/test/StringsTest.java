package lol.pyr.extendedcommands.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static lol.pyr.extendedcommands.test.util.ContextUtil.*;


public class StringsTest {
    @Test
    public void testDumpAll() {
        assertEquals("a b c", createContext("a", "b", "c").dumpAllArgs());
        assertEquals("a", createContext("a").dumpAllArgs());
        assertEquals("testing this very cool thing", createContext("testing", "this", "very", "cool", "thing").dumpAllArgs());
    }
}
