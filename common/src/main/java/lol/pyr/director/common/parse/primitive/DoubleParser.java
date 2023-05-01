package lol.pyr.director.common.parse.primitive;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.parse.ParserType;

import java.util.Deque;

public abstract class DoubleParser<S> implements ParserType<Double, S> {
    @Override
    public Double parse(Deque<String> args) throws CommandExecutionException {
        try {
            return Double.parseDouble(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new CommandExecutionException();
        }
    }
}
