package lol.pyr.director.spigot.parse.primitive;

import lol.pyr.director.common.command.CommandContext;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.primitive.BooleanParser;
import org.bukkit.command.CommandSender;

public class SpigotBooleanParser extends BooleanParser<CommandSender> {
    private final Message<CommandSender> message;

    public SpigotBooleanParser(Message<CommandSender> message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext<CommandSender> context) {
        message.send(context);
    }
}
