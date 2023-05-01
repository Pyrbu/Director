package lol.pyr.director.common.message;

import lol.pyr.director.common.command.CommandContext;

public interface Message<R> {
    void send(CommandContext<R> context);
}
