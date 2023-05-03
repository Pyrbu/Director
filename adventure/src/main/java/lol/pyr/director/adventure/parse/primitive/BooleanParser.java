package lol.pyr.director.adventure.parse.primitive;

import lol.pyr.director.adventure.command.CommandContext;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.primitive.CommonBooleanParser;

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
