package lol.pyr.extendedcommands.parsers.primitive;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;

import java.util.Deque;

/**
 * An implementation of {@link ParserType} for parsing the {@link Integer} class
 */
public class IntegerParser implements ParserType<Integer> {
    /**
     * Parses a stack of strings into a {@link Integer}
     *
     * @param args The arguments that are provided for parsing
     * @return A parsed {@link Integer}
     * @throws ParsingException When parsing could not be completed
     */
    @Override
    public Integer parse(Deque<String> args) throws ParsingException {
        try {
            return Integer.parseInt(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new ParsingException(getClass());
        }
    }
}
