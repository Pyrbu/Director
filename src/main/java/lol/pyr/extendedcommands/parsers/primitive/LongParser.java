package lol.pyr.extendedcommands.parsers.primitive;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;

import java.util.Deque;

/**
 * An implementation of {@link ParserType} for parsing the {@link Long} class
 */
public class LongParser implements ParserType<Long> {
    /**
     * Parses a stack of strings into a {@link Long}
     *
     * @param args The arguments that are provided for parsing
     * @return A parsed {@link Long}
     * @throws ParsingException When parsing could not be completed
     */
    @Override
    public Long parse(Deque<String> args) throws ParsingException {
        try {
            return Long.parseLong(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new ParsingException(getClass());
        }
    }
}
