package lol.pyr.director.spigot.parse;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.ParserType;
import lol.pyr.director.spigot.command.SpigotCommandContext;

public abstract class SpigotParser<T> implements ParserType<T, SpigotCommandContext> {
    private final Message<SpigotCommandContext> message;

    public SpigotParser(Message<SpigotCommandContext> message) {
        this.message = message;
    }

    @Override
    public void send(SpigotCommandContext context) {
        message.send(context);
    }
}
