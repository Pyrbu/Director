package lol.pyr.director.adventure.parse.primitive;

import lol.pyr.director.adventure.command.CommandContext;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.primitive.CommonShortParser;

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
