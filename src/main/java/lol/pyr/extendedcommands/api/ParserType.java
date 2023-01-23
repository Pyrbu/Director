package lol.pyr.extendedcommands.api;

import lol.pyr.extendedcommands.exception.ParsingException;

import java.util.Deque;

/**
 * A class that can be implemented to create a custom parser type
 * Remember to register the parser type to the {@link lol.pyr.extendedcommands.CommandManager}
 * using {@link lol.pyr.extendedcommands.CommandManager#registerParser(Class, ParserType)}
 *
 * @param <T> The type that this parser parses
 */
public interface ParserType<T> {
    /**
     * The function that parses a stack of strings into T
     *
     * @param args The arguments that are provided for parsing
     * @return The parsed value
     * @throws ParsingException When parsing failed (with its own class as the parameter)
     */
    T parse(Deque<String> args) throws ParsingException;
}
