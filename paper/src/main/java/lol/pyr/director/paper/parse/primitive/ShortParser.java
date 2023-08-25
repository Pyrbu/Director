package lol.pyr.director.paper.parse.primitive;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.primitive.CommonShortParser;
import lol.pyr.director.paper.command.CommandContext;

@SuppressWarnings("unused")
public class ShortParser extends CommonShortParser<CommandContext> {
    private final Message<CommandContext> message;

    public ShortParser(Message<CommandContext> message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext context) {
        message.send(context);
    }
}
