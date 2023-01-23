package lol.pyr.extendedcommands.parsers.primitive;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;

import java.util.Deque;

/**
 * An implementation of {@link ParserType} for parsing the {@link Short} class
 */
public class ShortParser implements ParserType<Short> {
    /**
     * Parses a stack of strings into a {@link Short}
     *
     * @param args The arguments that are provided for parsing
     * @return A parsed {@link Short}
     * @throws ParsingException When parsing could not be completed
     */
    @Override
    public Short parse(Deque<String> args) throws ParsingException {
        try {
            return Short.parseShort(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new ParsingException(getClass());
        }
    }
}
