package lol.pyr.director.common.parse.primitive;

import lol.pyr.director.common.parse.CommonParserType;
import lol.pyr.director.common.command.CommandExecutionException;

import java.util.Deque;

public abstract class CommonShortParser<S> implements CommonParserType<Short, S> {
    @Override
    public Short parse(Deque<String> args) throws CommandExecutionException {
        try {
            return Short.parseShort(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new CommandExecutionException();
        }
    }
}
