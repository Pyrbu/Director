package lol.pyr.director.spigot.message;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.spigot.command.CommandContext;
import net.kyori.adventure.text.Component;

public class StaticComponentMessage implements Message<CommandContext> {
    private final Component message;

    public StaticComponentMessage(Component message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext context) {
        context.getSender().sendMessage(message);
    }
}