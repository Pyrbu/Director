package lol.pyr.director.common.parse.primitive;

import lol.pyr.director.common.parse.CommonParserType;
import lol.pyr.director.common.command.CommandExecutionException;

import java.util.Deque;

public abstract class CommonFloatParser<S> implements CommonParserType<Float, S> {
    @Override
    public Float parse(Deque<String> args) throws CommandExecutionException {
        try {
            return Float.parseFloat(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new CommandExecutionException();
        }
    }
}
