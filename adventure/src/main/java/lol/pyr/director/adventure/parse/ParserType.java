package lol.pyr.director.adventure.parse;

import lol.pyr.director.adventure.command.CommandContext;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.CommonParserType;

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
