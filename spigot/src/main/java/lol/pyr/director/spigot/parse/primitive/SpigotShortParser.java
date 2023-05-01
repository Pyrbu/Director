package lol.pyr.director.spigot.parse.primitive;

import lol.pyr.director.common.command.CommandContext;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.primitive.ShortParser;
import org.bukkit.command.CommandSender;

public class SpigotShortParser extends ShortParser<CommandSender> {
    private final Message<CommandSender> message;

    public SpigotShortParser(Message<CommandSender> message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext<CommandSender> context) {
        message.send(context);
    }
}
