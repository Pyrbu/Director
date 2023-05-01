package lol.pyr.director.spigot.parse;

import lol.pyr.director.common.command.CommandContext;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.ParserType;
import org.bukkit.command.CommandSender;

public abstract class SpigotParser<T> implements ParserType<T, CommandSender> {
    private final Message<CommandSender> message;

    public SpigotParser(Message<CommandSender> message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext<CommandSender> context) {
        message.send(context);
    }
}
