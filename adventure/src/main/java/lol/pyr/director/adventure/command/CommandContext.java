package lol.pyr.director.adventure.command;

import lol.pyr.director.adventure.message.StaticComponentMessage;
import lol.pyr.director.adventure.message.StaticMessage;
import lol.pyr.director.common.command.CommonCommandContext;
import lol.pyr.director.common.command.CommandExecutionException;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Deque;
import java.util.List;

@SuppressWarnings("unused")
public class CommandContext extends CommonCommandContext<CommandManager, CommandContext, CommandSender, CommandHandler> {
    private final Audience audience;

    public CommandContext(CommandManager manager, CommandSender sender, String label, Deque<String> args) {
        super(manager, sender, label, args);
        audience = manager.getAudiences().sender(sender);
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

    public Audience getAudience() {
        return audience;
    }

    public void halt(Component message) throws CommandExecutionException {
        setLastMessage(new StaticComponentMessage(message));
        throw new CommandExecutionException();
    }

    public void send(Component message) {
        getAudience().sendMessage(message);
    }

    public boolean hasPermission(String string) {
        return getSender().hasPermission(string);
    }
}
