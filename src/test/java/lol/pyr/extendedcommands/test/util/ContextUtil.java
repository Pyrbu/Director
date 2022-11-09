package lol.pyr.extendedcommands.test.util;

import lol.pyr.extendedcommands.CommandContext;
import lol.pyr.extendedcommands.util.StackUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class ContextUtil {
    public static CommandContext<JavaPlugin> createContext(String... args) {
        return new CommandContext<>(null, null, null, null, StackUtil.fromArray(args));
    }
}
