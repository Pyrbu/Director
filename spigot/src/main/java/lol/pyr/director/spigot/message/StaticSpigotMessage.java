package lol.pyr.director.spigot.message;

import lol.pyr.director.common.command.CommandContext;
import lol.pyr.director.common.message.Message;
import org.bukkit.command.CommandSender;

public class StaticSpigotMessage implements Message<CommandSender> {
    private final String message;

    public StaticSpigotMessage(String message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext<CommandSender> context) {
        context.getSender().sendMessage(message);
    }
}
