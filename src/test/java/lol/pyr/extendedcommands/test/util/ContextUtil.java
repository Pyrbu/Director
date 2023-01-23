package lol.pyr.extendedcommands.test.util;

import lol.pyr.extendedcommands.CommandContext;
import lol.pyr.extendedcommands.CommandManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ContextUtil {
    private static final Constructor<CommandContext> constructor;

    static {
        try {
            constructor = CommandContext.class.getDeclaredConstructor(CommandManager.class, CommandSender.class, Command.class, String.class, Deque.class);
            constructor.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static CommandContext createContext(String... args) {
        try {
            return constructor.newInstance(null, null, null, null, new LinkedList<>(List.of(args)));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
