package lol.pyr.extendedcommands;

import lol.pyr.extendedcommands.exception.CommandExecutionException;
import lol.pyr.extendedcommands.exception.ParsingException;
import lol.pyr.extendedcommands.util.CompletionUtil;
import lol.pyr.extendedcommands.util.StackUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

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
        List<String> list = new ArrayList<>(args);
        Collections.reverse(list);
        return String.join(" ", list);
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

    public boolean matchCompletion(String... args) {
        if (args.length != getArgs().size()) return false;
        if (args.length == 0) return true;
        Stack<String> clone = StackUtil.clone(getArgs());
        for (String s : args) if (!s.equals("*") && !s.equalsIgnoreCase(clone.pop())) return false;
        return true;
    }

    public List<String> completePlayers() throws CommandExecutionException {
        return CompletionUtil.players(popString());
    }

    public List<String> completeLiteral(String... args) throws CommandExecutionException {
        return CompletionUtil.literal(popString(), args);
    }

    public <T extends Enum<T>> List<String> completeEnum(T[] e) throws CommandExecutionException {
        return CompletionUtil.enums(popString(), e);
    }

    public List<String> completeCollection(Collection<String> args) throws CommandExecutionException {
        return CompletionUtil.filter(popString(), args.stream());
    }
}
