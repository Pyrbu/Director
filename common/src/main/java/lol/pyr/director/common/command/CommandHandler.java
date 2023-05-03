package lol.pyr.director.common.command;

import java.util.Collections;
import java.util.List;

public interface CommandHandler<Context> {
    void run(Context context) throws CommandExecutionException;
    default List<String> suggest(Context context) throws CommandExecutionException{
        return Collections.emptyList();
    }
}
