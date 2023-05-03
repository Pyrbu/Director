package lol.pyr.director.spigot.message;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.spigot.command.SpigotCommandContext;

public class StaticSpigotMessage implements Message<SpigotCommandContext> {
    private final String message;

    public StaticSpigotMessage(String message) {
        this.message = message;
    }

    @Override
    public void send(SpigotCommandContext context) {
        context.getSender().sendMessage(message);
    }
}
