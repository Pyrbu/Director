package lol.pyr.extendedcommands.parsers.primitive;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;

import java.util.Stack;

public class IntegerParser implements ParserType<Integer> {
    @Override
    public Integer parse(Stack<String> args) throws ParsingException {
        try {
            return Integer.parseInt(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new ParsingException(getClass());
        }
    }
}
