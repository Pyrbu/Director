package lol.pyr.director.spigot.parse;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.CommonParserType;
import lol.pyr.director.spigot.command.CommandContext;

public abstract class ParserType<T> implements CommonParserType<T, CommandContext> {
    private final Message<CommandContext> message;

    public ParserType(Message<CommandContext> message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext context) {
        message.send(context);
    }
}
