package lol.pyr.extendedcommands.exception;

import lol.pyr.extendedcommands.api.ParserType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

/**
 * The exception thrown when a {@link ParserType} fails to parse
 * this is always handled when thrown in an {@link lol.pyr.extendedcommands.api.ExtendedExecutor}
 * The class field of this exception corresponds to the message sent
 * when the parsing fails, if you wish to customise this message please
 * use {@link lol.pyr.extendedcommands.CommandManager#setMessageResolver(Class, Function)}
 */
@RequiredArgsConstructor
@Getter
public class ParsingException extends Throwable {
    private final Class<? extends ParserType<?>> clazz;
}
