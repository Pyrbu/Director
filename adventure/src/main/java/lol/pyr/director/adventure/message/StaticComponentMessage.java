package lol.pyr.director.adventure.message;

import lol.pyr.director.adventure.command.CommandContext;
import lol.pyr.director.common.message.Message;
import net.kyori.adventure.text.Component;

public class StaticComponentMessage implements Message<CommandContext> {
    private final Component message;

    public StaticComponentMessage(Component message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext context) {
        context.getAudience().sendMessage(message);
    }
}