package lol.pyr.director.spigot.command;

import lol.pyr.director.common.command.CommandContext;
import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.spigot.message.StaticSpigotMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Deque;
import java.util.List;

@SuppressWarnings("unused")
public class SpigotCommandContext extends CommandContext<SpigotCommandManager, SpigotCommandContext, CommandSender, SpigotCommandHandler> {
    public SpigotCommandContext(SpigotCommandManager manager, CommandSender sender, String label, Deque<String> args) {
        super(manager, sender, label, args);
    }

    public List<String> suggestPlayers() throws CommandExecutionException {
        return suggestStream(Bukkit.getOnlinePlayers().stream().map(Player::getName));
    }

    public void halt(String message) throws CommandExecutionException {
        setLastMessage(new StaticSpigotMessage(message));
        throw new CommandExecutionException();
    }

    public void send(String message) {
        getSender().sendMessage(message);
    }
}
