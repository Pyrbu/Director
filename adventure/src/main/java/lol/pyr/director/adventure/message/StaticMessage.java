package lol.pyr.director.adventure.message;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.adventure.command.CommandContext;

public class StaticMessage implements Message<CommandContext> {
    private final String message;

    public StaticMessage(String message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext context) {
        context.getSender().sendMessage(message);
    }
}
