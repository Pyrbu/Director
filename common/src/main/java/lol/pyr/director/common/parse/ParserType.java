package lol.pyr.director.common.parse;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.message.Message;

import java.util.Deque;

public interface ParserType<T, S> extends Message<S>  {
    T parse(Deque<String> args) throws CommandExecutionException;
}
