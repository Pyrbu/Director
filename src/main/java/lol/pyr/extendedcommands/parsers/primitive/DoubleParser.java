package lol.pyr.extendedcommands.parsers.primitive;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;

import java.util.Deque;

/**
 * An implementation of {@link ParserType} for parsing the {@link Double} class
 */
public class DoubleParser implements ParserType<Double> {
    /**
     * Parses a stack of strings into a {@link Double}
     *
     * @param args The arguments that are provided for parsing
     * @return A parsed {@link Double}
     * @throws ParsingException When parsing could not be completed
     */
    @Override
    public Double parse(Deque<String> args) throws ParsingException {
        try {
            return Double.parseDouble(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new ParsingException(getClass());
        }
    }
}
