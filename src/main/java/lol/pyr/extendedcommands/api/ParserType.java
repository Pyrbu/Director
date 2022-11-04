package lol.pyr.extendedcommands.api;

import lol.pyr.extendedcommands.exception.ParsingException;

import java.util.Stack;

public interface ParserType<T> {
    T parse(Stack<String> args) throws ParsingException;
}
