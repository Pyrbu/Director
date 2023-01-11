package lol.pyr.extendedcommands.api;

import lol.pyr.extendedcommands.CommandContext;
import lol.pyr.extendedcommands.exception.CommandExecutionException;

import java.util.Collection;
import java.util.List;

/**
 * The class that can be implemented to create command
 * logic using the {@link CommandContext} API
 */
@SuppressWarnings({"unused", "RedundantThrows"})
public interface ExtendedExecutor {
    /**
     * The method that contains all of the command logic
     *
     * @param context The execution context, contains all the information that
     *                a regular bukkit command would have.
     * @throws CommandExecutionException When command execution needs to be halted (this exception is always handled)
     */
    void run(CommandContext context) throws CommandExecutionException;

    /**
     *
     * @param context The execution context, contains all the information that
     *                a regular bukkit command would have.
     * @return A list of possible completions, see {@link CommandContext#completeLiteral(String...)}, {@link CommandContext#completeCollection(Collection)}, {@link CommandContext#completePlayers()} and {@link CommandContext#completeEnum(Enum[])}
     * @throws CommandExecutionException When command execution needs to be halted (this exception is always handled)
     */
    default List<String> complete(CommandContext context) throws CommandExecutionException {
        return List.of();
    }
}
