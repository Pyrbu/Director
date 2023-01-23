package lol.pyr.extendedcommands.parsers.primitive;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;

import java.util.Deque;

/**
 * An implementation of {@link ParserType} for parsing the {@link Float} class
 */
public class FloatParser implements ParserType<Float> {
    /**
     * Parses a stack of strings into a {@link Float}
     *
     * @param args The arguments that are provided for parsing
     * @return A parsed {@link Float}
     * @throws ParsingException When parsing could not be completed
     */
    @Override
    public Float parse(Deque<String> args) throws ParsingException {
        try {
            return Float.parseFloat(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new ParsingException(getClass());
        }
    }
}
