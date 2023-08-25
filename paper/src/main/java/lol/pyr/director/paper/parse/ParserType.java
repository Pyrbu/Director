package lol.pyr.director.paper.parse;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.CommonParserType;
import lol.pyr.director.paper.command.CommandContext;

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
