package lol.pyr.extendedcommands.parsers.util;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;

import java.util.Deque;
import java.util.UUID;

public class UUIDParser implements ParserType<UUID> {
    @Override
    public UUID parse(Deque<String> args) throws ParsingException {
        try {
            return UUID.fromString(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new ParsingException(getClass());
        }
    }
}
