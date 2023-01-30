package lol.pyr.extendedcommands.test;

import lol.pyr.extendedcommands.CommandContext;
import lol.pyr.extendedcommands.multicommands.MultiCommand;
import lol.pyr.extendedcommands.api.ExtendedExecutor;
import lol.pyr.extendedcommands.exception.CommandExecutionException;
import lol.pyr.extendedcommands.test.util.ContextUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MultiCommandTest {
    @Test
    public void testMultiCommandCompletion() throws CommandExecutionException {
        MultiCommand cmd = new MultiCommand(c -> "")
                .addSubcommand("test", new ExtendedExecutor() {
                    @Override
                    public void run(CommandContext context) {}

                    @Override
                    public List<String> complete(CommandContext context) throws CommandExecutionException {
                        if (context.matchCompletion()) return context.completeLiteral("hi");
                        if (context.matchCompletion("a", "b", "c")) return context.completeLiteral("amongus", "bozo");
                        return List.of();
                    }
                })
                .addSubcommand("test2", new ExtendedExecutor() {
                    @Override
                    public void run(CommandContext context) {}

                    @Override
                    public List<String> complete(CommandContext context) throws CommandExecutionException {
                        if (context.matchCompletion()) return context.completeLiteral("kek");
                        return List.of();
                    }
                });
        Assertions.assertEquals(List.of("hi"), cmd.complete(ContextUtil.createContext("test", "")));
        Assertions.assertEquals(List.of("amongus", "bozo"), cmd.complete(ContextUtil.createContext("test", "a", "b", "c", "")));
        Assertions.assertEquals(List.of("kek"), cmd.complete(ContextUtil.createContext("test2", "")));
        Assertions.assertEquals(List.of(), cmd.complete(ContextUtil.createContext("test2", "aaa")));
    }
}
