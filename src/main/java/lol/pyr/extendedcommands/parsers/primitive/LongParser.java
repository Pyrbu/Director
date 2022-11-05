package lol.pyr.extendedcommands.parsers.primitive;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;

import java.util.Stack;

public class LongParser implements ParserType<Long> {
    @Override
    public Long parse(Stack<String> args) throws ParsingException {
        try {
            return Long.parseLong(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new ParsingException(getClass());
        }
    }
}
