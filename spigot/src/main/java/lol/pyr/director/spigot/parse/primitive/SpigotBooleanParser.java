package lol.pyr.director.spigot.parse.primitive;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.primitive.BooleanParser;
import lol.pyr.director.spigot.command.SpigotCommandContext;

@SuppressWarnings("unused")
public class SpigotBooleanParser extends BooleanParser<SpigotCommandContext> {
    private final Message<SpigotCommandContext> message;

    public SpigotBooleanParser(Message<SpigotCommandContext> message) {
        this.message = message;
    }

    @Override
    public void send(SpigotCommandContext context) {
        message.send(context);
    }
}
