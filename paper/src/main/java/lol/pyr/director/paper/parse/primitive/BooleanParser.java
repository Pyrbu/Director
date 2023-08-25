package lol.pyr.director.paper.parse.primitive;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.primitive.CommonBooleanParser;
import lol.pyr.director.paper.command.CommandContext;

@SuppressWarnings("unused")
public class BooleanParser extends CommonBooleanParser<CommandContext> {
    private final Message<CommandContext> message;

    public BooleanParser(Message<CommandContext> message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext context) {
        message.send(context);
    }
}
