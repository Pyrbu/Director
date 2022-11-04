package lol.pyr.extendedcommands.parsers;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;

import java.util.Stack;

public class DoubleParser implements ParserType<Double> {
    @Override
    public Double parse(Stack<String> args) throws ParsingException {
        try {
            return Double.parseDouble(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new ParsingException(getClass());
        }
    }
}
