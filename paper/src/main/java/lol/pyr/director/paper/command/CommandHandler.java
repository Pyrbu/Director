package lol.pyr.director.paper.command;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.command.CommonCommandHandler;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("RedundantThrows")
public interface CommandHandler extends CommonCommandHandler<CommandContext> {
    void run(CommandContext context) throws CommandExecutionException;
    default List<String> suggest(CommandContext context) throws CommandExecutionException {
        return Collections.emptyList();
    }
}
