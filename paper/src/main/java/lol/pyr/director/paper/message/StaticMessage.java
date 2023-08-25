package lol.pyr.director.paper.message;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.paper.command.CommandContext;

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
