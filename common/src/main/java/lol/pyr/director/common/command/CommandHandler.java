package lol.pyr.director.common.command;

import java.util.Collections;
import java.util.List;

public interface CommandHandler<S> {
    void run(CommandContext<S> context) throws CommandExecutionException;
    default List<String> suggest(CommandContext<S> context) throws CommandExecutionException{
        return Collections.emptyList();
    }
}
