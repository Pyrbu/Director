package lol.pyr.director.spigot.command;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.command.CommandHandler;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("RedundantThrows")
public interface SpigotCommandHandler extends CommandHandler<SpigotCommandContext> {
    void run(SpigotCommandContext context) throws CommandExecutionException;
    default List<String> suggest(SpigotCommandContext context) throws CommandExecutionException {
        return Collections.emptyList();
    }
}
