package lol.pyr.director.standalone.command;

import lol.pyr.director.common.command.CommandContext;
import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.command.CommandHandler;
import lol.pyr.director.standalone.message.DirectorReceiver;

import java.util.Collections;
import java.util.List;

@SuppressWarnings({"UnusedReturnValue", "unused", "RedundantThrows"})
public interface StandaloneCommandHandler extends CommandHandler<DirectorReceiver> {
    void run(StandaloneCommandContext context) throws CommandExecutionException;
    default List<String> suggest(StandaloneCommandContext context) throws CommandExecutionException {
        return Collections.emptyList();
    }

    default void run(CommandContext<DirectorReceiver> context) throws CommandExecutionException {
        if (context instanceof StandaloneCommandContext) run((StandaloneCommandContext) context);
        throw new UnsupportedOperationException("StandaloneCommandHandler only accepts StandaloneCommandContext");
    }

    default List<String> suggest(CommandContext<DirectorReceiver> context) throws CommandExecutionException{
        if (context instanceof StandaloneCommandContext) suggest((StandaloneCommandContext) context);
        throw new UnsupportedOperationException("StandaloneCommandHandler only accepts StandaloneCommandContext");
    }
}
