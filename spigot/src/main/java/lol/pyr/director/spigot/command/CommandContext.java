package lol.pyr.director.spigot.command;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.command.CommonCommandContext;
import lol.pyr.director.spigot.message.StaticMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Deque;
import java.util.List;

@SuppressWarnings("unused")
public class CommandContext extends CommonCommandContext<CommandManager, CommandContext, CommandSender, CommandHandler> {
    public CommandContext(CommandManager manager, CommandSender sender, String label, Deque<String> args) {
        super(manager, sender, label, args);
    }

    public List<String> suggestPlayers() throws CommandExecutionException {
        return suggestStream(Bukkit.getOnlinePlayers().stream().map(Player::getName));
    }

    public Player ensureSenderIsPlayer() throws CommandExecutionException {
        if (getSender() instanceof Player) return (Player) getSender();
        throw new CommandExecutionException();
    }

    public void halt(String message) throws CommandExecutionException {
        setLastMessage(new StaticMessage(message));
        throw new CommandExecutionException();
    }

    public void send(String message) {
        getSender().sendMessage(message);
    }

    public boolean hasPermission(String string) {
        return getSender().hasPermission(string);
    }
}
