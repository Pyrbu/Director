package lol.pyr.director.paper.parse.primitive;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.primitive.CommonIntegerParser;
import lol.pyr.director.paper.command.CommandContext;

@SuppressWarnings("unused")
public class IntegerParser extends CommonIntegerParser<CommandContext> {
    private final Message<CommandContext> message;

    public IntegerParser(Message<CommandContext> message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext context) {
        message.send(context);
    }
}
