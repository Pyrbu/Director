package lol.pyr.director.spigot.parse.primitive;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.primitive.CommonLongParser;
import lol.pyr.director.spigot.command.CommandContext;

@SuppressWarnings("unused")
public class LongParser extends CommonLongParser<CommandContext> {
    private final Message<CommandContext> message;

    public LongParser(Message<CommandContext> message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext context) {
        message.send(context);
    }
}
