package lol.pyr.director.spigot.command;

import lol.pyr.director.common.command.CommandContext;
import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.command.CommandHandler;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public interface SpigotCommandHandler extends CommandHandler<CommandSender> {
    void run(SpigotCommandContext context) throws CommandExecutionException;
    default List<String> suggest(SpigotCommandContext context) throws CommandExecutionException{
        return Collections.emptyList();
    }

    default void run(CommandContext<CommandSender> context) throws CommandExecutionException {
        if (context instanceof SpigotCommandContext) run((SpigotCommandContext) context);
        throw new UnsupportedOperationException("SpigotCommandHandler only accepts SpigotCommandContext");
    }

    default List<String> suggest(CommandContext<CommandSender> context) throws CommandExecutionException{
        if (context instanceof SpigotCommandContext) suggest((SpigotCommandContext) context);
        throw new UnsupportedOperationException("SpigotCommandHandler only accepts SpigotCommandContext");
    }
}
