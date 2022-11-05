package lol.pyr.extendedcommands;

import lol.pyr.extendedcommands.exception.CommandExecutionException;
import lol.pyr.extendedcommands.exception.ParsingException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EmptyStackException;
import java.util.Stack;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@Getter
public class CommandContext <P extends JavaPlugin> {
    private final CommandManager<P> manager;
    private final CommandSender sender;
    private final Command command;
    private final String label;
    private final Stack<String> args;
    @Setter private String currentUsage = "";

    public P getPlugin() {
        return manager.getPlugin();
    }

    public <T> T parse(Class<T> type) throws CommandExecutionException {
        try {
            return manager.parse(type, args);
        } catch (ParsingException exception) {
            throw new CommandExecutionException(manager.getMessage(exception.getClazz(), this));
        } catch (EmptyStackException exception) {
            throw new CommandExecutionException(manager.getMessage(MessageKey.NOT_ENOUGH_ARGS, this));
        }
    }

    public Player ensureSenderIsPlayer() throws CommandExecutionException {
        if (sender instanceof Player player) return player;
        throw new CommandExecutionException(manager.getMessage(MessageKey.SENDER_REQUIRED_PLAYER, this));
    }

    public void ensureArgsNotEmpty() throws CommandExecutionException {
        if (argSize() == 0) throw new CommandExecutionException(manager.getMessage(MessageKey.NOT_ENOUGH_ARGS, this));
    }

    public Player parseTargetOrSelf(String allowOtherPermission) throws CommandExecutionException {
        if (!hasPermission(allowOtherPermission) || argSize() == 0) return ensureSenderIsPlayer();
        return parse(Player.class);
    }

    public String dumpAllArgs() {
        StringBuilder builder = new StringBuilder();
        while (!args.empty()) builder.append(args.pop());
        return builder.toString();
    }

    public String popString() throws CommandExecutionException {
        if (argSize() == 0) throw new CommandExecutionException(manager.getMessage(MessageKey.NOT_ENOUGH_ARGS, this));
        return args.pop();
    }

    public int argSize() {
        return args.size();
    }

    public boolean hasPermission(String string) {
        return sender.hasPermission(string);
    }

}
