package lol.pyr.extendedcommands.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommandExecutionException extends Throwable {
    private final String message;
}
