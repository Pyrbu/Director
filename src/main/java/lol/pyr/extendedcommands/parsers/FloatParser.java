package lol.pyr.extendedcommands.parsers;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;

import java.util.Stack;

public class FloatParser implements ParserType<Float> {
    @Override
    public Float parse(Stack<String> args) throws ParsingException {
        try {
            return Float.parseFloat(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new ParsingException(getClass());
        }
    }
}
