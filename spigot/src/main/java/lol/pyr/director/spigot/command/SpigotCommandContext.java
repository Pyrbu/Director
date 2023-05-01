package lol.pyr.director.spigot.command;

import lol.pyr.director.common.command.CommandContext;
import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.command.CommandManager;
import lol.pyr.director.spigot.message.StaticSpigotMessage;
import org.bukkit.command.CommandSender;

import java.util.Deque;

public class SpigotCommandContext extends CommandContext<CommandSender> {
    public SpigotCommandContext(CommandManager<CommandSender> manager, CommandSender sender, String label, Deque<String> args) {
        super(manager, sender, label, args);
    }

    public void halt(String message) throws CommandExecutionException {
        setLastMessage(new StaticSpigotMessage(message));
        throw new CommandExecutionException();
    }
}
