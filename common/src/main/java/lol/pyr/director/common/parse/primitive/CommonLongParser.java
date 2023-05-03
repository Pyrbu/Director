package lol.pyr.director.common.parse.primitive;

import lol.pyr.director.common.parse.CommonParserType;
import lol.pyr.director.common.command.CommandExecutionException;

import java.util.Deque;

public abstract class CommonLongParser<S> implements CommonParserType<Long, S> {
    @Override
    public Long parse(Deque<String> args) throws CommandExecutionException {
        try {
            return Long.parseLong(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new CommandExecutionException();
        }
    }
}
