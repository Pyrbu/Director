package lol.pyr.director.standalone.command;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.command.CommonCommandHandler;

import java.util.Collections;
import java.util.List;

@SuppressWarnings({"UnusedReturnValue", "unused", "RedundantThrows"})
public interface CommandHandler extends CommonCommandHandler<CommandContext> {
    void run(CommandContext context) throws CommandExecutionException;

    default List<String> suggest(CommandContext context) throws CommandExecutionException {
        return Collections.emptyList();
    }
}
