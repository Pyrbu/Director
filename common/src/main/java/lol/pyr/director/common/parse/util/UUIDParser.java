package lol.pyr.director.common.parse.util;

import lol.pyr.director.common.parse.ParserType;
import lol.pyr.director.common.command.CommandExecutionException;

import java.util.Deque;
import java.util.UUID;

public abstract class UUIDParser<S> implements ParserType<UUID, S> {
    @Override
    public UUID parse(Deque<String> args) throws CommandExecutionException {
        try {
            return UUID.fromString(args.pop());
        } catch (IllegalArgumentException exception) {
            throw new CommandExecutionException();
        }
    }
}
