package lol.pyr.extendedcommands.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The exception type thrown when execution of a command needs
 * to be abruptly stopped, this is always handled when thrown in
 * an {@link lol.pyr.extendedcommands.api.ExtendedExecutor} when registered through a {@link lol.pyr.extendedcommands.CommandManager}
 */
@AllArgsConstructor
@Getter
public class CommandExecutionException extends Throwable {
    private final String message;
}
