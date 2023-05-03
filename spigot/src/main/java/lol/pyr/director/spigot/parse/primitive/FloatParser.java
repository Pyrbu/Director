package lol.pyr.director.spigot.parse.primitive;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.primitive.CommonFloatParser;
import lol.pyr.director.spigot.command.CommandContext;

@SuppressWarnings("unused")
public class FloatParser extends CommonFloatParser<CommandContext> {
    private final Message<CommandContext> message;

    public FloatParser(Message<CommandContext> message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext context) {
        message.send(context);
    }
}
