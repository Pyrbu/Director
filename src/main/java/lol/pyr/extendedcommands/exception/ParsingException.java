package lol.pyr.extendedcommands.exception;

import lol.pyr.extendedcommands.api.ParserType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ParsingException extends Throwable {
    private final Class<? extends ParserType<?>> clazz;
}
