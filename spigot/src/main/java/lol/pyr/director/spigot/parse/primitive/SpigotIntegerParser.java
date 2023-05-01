package lol.pyr.director.spigot.parse.primitive;

import lol.pyr.director.common.command.CommandContext;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.primitive.IntegerParser;
import org.bukkit.command.CommandSender;

public class SpigotIntegerParser extends IntegerParser<CommandSender> {
    private final Message<CommandSender> message;

    public SpigotIntegerParser(Message<CommandSender> message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext<CommandSender> context) {
        message.send(context);
    }
}
