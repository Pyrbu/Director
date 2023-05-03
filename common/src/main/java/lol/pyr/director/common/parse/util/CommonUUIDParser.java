package lol.pyr.director.common.parse.util;

import lol.pyr.director.common.parse.CommonParserType;
import lol.pyr.director.common.command.CommandExecutionException;

import java.util.Deque;
import java.util.UUID;

@SuppressWarnings("unused")
public abstract class CommonUUIDParser<S> implements CommonParserType<UUID, S> {
    @Override
    public UUID parse(Deque<String> args) throws CommandExecutionException {
        try {
            return UUID.fromString(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new CommandExecutionException();
        }
    }
}
