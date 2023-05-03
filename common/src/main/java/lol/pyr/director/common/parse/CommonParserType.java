package lol.pyr.director.common.parse;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.message.Message;

import java.util.Deque;

public interface CommonParserType<Type, Context> extends Message<Context>  {
    Type parse(Deque<String> args) throws CommandExecutionException;
}
