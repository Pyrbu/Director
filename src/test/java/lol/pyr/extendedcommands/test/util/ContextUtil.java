package lol.pyr.extendedcommands.test.util;

import lol.pyr.extendedcommands.CommandContext;
import lol.pyr.extendedcommands.CommandManager;
import lol.pyr.extendedcommands.util.StackUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

public class ContextUtil {
    private static final Constructor<CommandContext> constructor;

    static {
        try {
            constructor = CommandContext.class.getDeclaredConstructor(CommandManager.class, CommandSender.class, Command.class, String.class, Stack.class);
            constructor.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static CommandContext createContext(String... args) {
        try {
            return constructor.newInstance(null, null, null, null, StackUtil.fromArray(args));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
