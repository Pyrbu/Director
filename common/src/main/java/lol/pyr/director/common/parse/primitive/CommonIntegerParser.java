package lol.pyr.director.common.parse.primitive;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.parse.CommonParserType;

import java.util.Deque;

public abstract class CommonIntegerParser<S> implements CommonParserType<Integer, S> {
    @Override
    public Integer parse(Deque<String> args) throws CommandExecutionException {
        try {
            return Integer.parseInt(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new CommandExecutionException();
        }
    }
}
