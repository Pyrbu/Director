package lol.pyr.extendedcommands.parsers.primitive;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;

import java.util.Stack;

public class ShortParser implements ParserType<Short> {
    @Override
    public Short parse(Stack<String> args) throws ParsingException {
        try {
            return Short.parseShort(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new ParsingException(getClass());
        }
    }
}
