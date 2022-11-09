package lol.pyr.extendedcommands.test;

import lol.pyr.extendedcommands.exception.CommandExecutionException;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lol.pyr.extendedcommands.test.util.ContextUtil.createContext;
import static org.junit.jupiter.api.Assertions.*;

public class CompletionTest {
    @Test
    public void testMatching() {
        assertTrue(createContext("a", "b", "c").matchCompletion("a", "b", "c"));
        assertTrue(createContext().matchCompletion());

        assertFalse(createContext("a", "b", "c").matchCompletion("a", "b"));
        assertFalse(createContext("a", "b").matchCompletion("a", "b", "c"));
        assertFalse(createContext().matchCompletion("a"));
    }

    @Test
    public void testCompletion() throws CommandExecutionException {
        assertEquals(List.of("testa", "testb"), createContext("test").completeLiteral("a", "b", "testa", "testb"));
        assertEquals(List.of("BEDROCK"), createContext("bedro").completeEnum(Material.values()));
        assertEquals(List.of("CREATIVE"), createContext("creati").completeEnum(GameMode.values()));
    }
}
