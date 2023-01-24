package lol.pyr.extendedcommands;

import lol.pyr.extendedcommands.exception.CommandExecutionException;
import lol.pyr.extendedcommands.exception.ParsingException;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The container class for all of the information provided when
 * a command is executed
 */
@SuppressWarnings("unused")
@Getter
public class CommandContext {
    private final CommandManager manager;
    private final CommandSender sender;
    private final Command command;
    private final String label;
    private final Deque<String> args;
    @Setter private String currentUsage = "";

    protected CommandContext(CommandManager manager, CommandSender sender, Command command, String label, Deque<String> args) {
        this.manager = manager;
        this.sender = sender;
        this.command = command;
        this.label = label;
        this.args = args;
    }

    /**
     * Utility method for retrieving the plugin that the command belongs to
     *
     * @return The plugin that the command belongs to
     */
    public JavaPlugin getPlugin() {
        return manager.getPlugin();
    }

    /**
     * Method that parses arguments from the context into a provided type using a pre-existing registered {@link lol.pyr.extendedcommands.api.ParserType}
     *
     * @param type The class of the variable you wish to type
     * @return The parsed variable
     * @param <T> The type of the variable you wish to parse
     * @throws CommandExecutionException When there is a parsing exception using the provided message resolver for the parsing type or when there isnt enough arguments using {@link MessageKey#NOT_ENOUGH_ARGS}
     */
    public <T> T parse(Class<T> type) throws CommandExecutionException {
        try {
            return manager.parse(type, args);
        } catch (ParsingException exception) {
            throw new CommandExecutionException(manager.getMessage(exception.getClazz(), this));
        } catch (NoSuchElementException exception) {
            throw new CommandExecutionException(manager.getMessage(MessageKey.NOT_ENOUGH_ARGS, this));
        }
    }

    /**
     * Utility method for making sure that the sender is a player and if so parsing and returning the player
     *
     * @return The sender parsed as a player
     * @throws CommandExecutionException If the sender isnt a player using {@link CommandManager#getMessage(MessageKey, CommandContext)} with {@link MessageKey#SENDER_REQUIRED_PLAYER} as the message
     */
    public Player ensureSenderIsPlayer() throws CommandExecutionException {
        if (sender instanceof Player player) return player;
        throw new CommandExecutionException(manager.getMessage(MessageKey.SENDER_REQUIRED_PLAYER, this));
    }

    /**
     * Utility method for making sure that there is still arguments in the context
     *
     * @throws CommandExecutionException When there arent arguments using {@link CommandManager#getMessage(MessageKey, CommandContext)} with {@link MessageKey#NOT_ENOUGH_ARGS} as the message
     */
    public void ensureArgsNotEmpty() throws CommandExecutionException {
        if (argSize() == 0) throw new CommandExecutionException(manager.getMessage(MessageKey.NOT_ENOUGH_ARGS, this));
    }

    /**
     * Utility method for parsing a player from the arguments if there is an argument present and the sender
     * has a provided permission, if one of those conditions arent met the method attempts to parse the sender
     * as a player and return that, if that fails {@link CommandExecutionException} is thrown
     *
     * @param allowOtherPermission The permision to check for, null if you dont want the permission check to happen
     * @return The parsed player
     * @throws CommandExecutionException When there is no player provided and the sender isnt a player
     */
    public Player parseTargetOrSelf(String allowOtherPermission) throws CommandExecutionException {
        if ((allowOtherPermission == null || !hasPermission(allowOtherPermission)) || argSize() == 0) return ensureSenderIsPlayer();
        return parse(Player.class);
    }

    /**
     * Dumps all of the remaining arguments in this context into one long string
     *
     * @return All of the remaining arguments joined by a space
     */
    public String dumpAllArgs() {
        return String.join(" ", args);
    }

    /**
     * Remove one argument from the top of the argument stack and return it
     *
     * @return The removed argument
     * @throws CommandExecutionException When there isnt enough arguments using {@link CommandManager#getMessage(MessageKey, CommandContext)} with {@link MessageKey#NOT_ENOUGH_ARGS} as the message
     */
    public String popString() throws CommandExecutionException {
        if (argSize() == 0) throw new CommandExecutionException(manager.getMessage(MessageKey.NOT_ENOUGH_ARGS, this));
        return args.removeFirst();
    }

    /**
     * @return The amount of arguments that are left in this command context
     */
    public int argSize() {
        return args.size();
    }

    /**
     * Helper method for determining if the sender has a permission or not
     *
     * @param string The permission to check
     * @return True if the sender has the permission or false if they don't
     */
    public boolean hasPermission(String string) {
        return sender.hasPermission(string);
    }

    public boolean matchCompletion(String... args) {
        if (args.length + 1 != getArgs().size()) return false;
        if (args.length == 0) return true;
        Deque<String> clone = new ArrayDeque<>(getArgs());
        for (String s : args) {
            String popped = clone.pop();
            if (!s.equals("*") && !s.equalsIgnoreCase(popped)) return false;
        }
        return true;
    }

    /**
     * Does exactly the same thing as {@link #completeCollection(Collection)} but returns all
     * matching player usernames instead
     *
     * @return List of player names that match the beginning of the current argument
     * @throws CommandExecutionException When there isnt enough arguments using {@link CommandManager#getMessage(MessageKey, CommandContext)} with {@link MessageKey#NOT_ENOUGH_ARGS} as the message
     */
    public List<String> completePlayers() throws CommandExecutionException {
        return completeStream(Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName));
    }

    /**
     * Does exactly the same thing as {@link #completeCollection(Collection)} but takes an array
     * of strings instead
     *
     * @param args Array of strings
     * @return List of strings that match the beginning of the current argument
     * @throws CommandExecutionException When there isnt enough arguments using {@link CommandManager#getMessage(MessageKey, CommandContext)} with {@link MessageKey#NOT_ENOUGH_ARGS} as the message
     */
    public List<String> completeLiteral(String... args) throws CommandExecutionException {
        return completeStream(Stream.of(args));
    }

    /**
     * Does exactly the same thing as {@link #completeCollection(Collection)} but takes in a list of
     * {@link Enum} values which are then converted to strings using {@link Enum#name()}
     *
     * @param e List of {@link Enum} values
     * @return List of strings that match the beginning of the current argument
     * @throws CommandExecutionException When there isnt enough arguments using {@link CommandManager#getMessage(MessageKey, CommandContext)} with {@link MessageKey#NOT_ENOUGH_ARGS} as the message
     */
    public List<String> completeEnum(Enum<?>[] e) throws CommandExecutionException {
        return completeStream(Arrays.stream(e).map(Enum::name));
    }

    /**
     * Returns a filtered list of the input where the returned
     * values match the next argument in this command context (ignores case)
     *
     * @param args List of all possible completion
     * @return List of strings that match the beginning of the current argument
     * @throws CommandExecutionException When there isnt enough arguments using {@link CommandManager#getMessage(MessageKey, CommandContext)} with {@link MessageKey#NOT_ENOUGH_ARGS} as the message
     */
    public List<String> completeCollection(Collection<String> args) throws CommandExecutionException {
        return completeStream(args.stream());
    }

    private List<String> completeStream(Stream<String> args) throws CommandExecutionException {
        ensureArgsNotEmpty();
        final String input = getArgs().removeLast().toLowerCase();
        return args.filter(s -> s.toLowerCase().startsWith(input)).collect(Collectors.toList());
    }

    /**
     * Immedietly stops execution of the command and sends
     * a provided message to the player tied to this context
     *
     * @param message The message to be sent to the player
     * @throws CommandExecutionException To stop execution
     */
    public void halt(String message) throws CommandExecutionException {
        throw new CommandExecutionException(message);
    }

    /**
     * Immedietly stops execution of the command without
     * sending a message to the player
     *
     * @throws CommandExecutionException To stop execution
     */
    public void halt() throws CommandExecutionException {
        halt(null);
    }

    /**
     * A utility method that simply sends a message
     * to the sender of the command
     * @param message The message to send
     */
    public void send(String message) {
        sender.sendMessage(message);
    }
}
