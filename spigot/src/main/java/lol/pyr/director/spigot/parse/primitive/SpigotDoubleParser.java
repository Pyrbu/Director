package lol.pyr.director.spigot.parse.primitive;

import lol.pyr.director.common.command.CommandContext;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.primitive.DoubleParser;
import org.bukkit.command.CommandSender;

public class SpigotDoubleParser extends DoubleParser<CommandSender> {
    private final Message<CommandSender> message;

    public SpigotDoubleParser(Message<CommandSender> message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext<CommandSender> context) {
        message.send(context);
    }
}
