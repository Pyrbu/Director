package lol.pyr.extendedcommands.api;

import lol.pyr.extendedcommands.CommandContext;
import lol.pyr.extendedcommands.exception.CommandExecutionException;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public interface ExtendedExecutor <P extends JavaPlugin> {
    void run(CommandContext<P> context) throws CommandExecutionException;

    default List<String> complete(CommandContext<P> context) throws CommandExecutionException {
        return List.of();
    }
}
