package lol.pyr.extendedcommands;

import lol.pyr.extendedcommands.api.ExtendedExecutor;
import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;
import lol.pyr.extendedcommands.parsers.GameModeParser;
import lol.pyr.extendedcommands.parsers.MaterialParser;
import lol.pyr.extendedcommands.parsers.PlayerParser;
import lol.pyr.extendedcommands.parsers.WorldParser;
import lol.pyr.extendedcommands.parsers.primitive.*;
import lombok.Getter;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * The class that handles the registration of commands, contains
 * all of the registered parser types and resolves all error messages
 */
@SuppressWarnings("unused")
public class CommandManager {
    @Getter private final JavaPlugin plugin;
    private final Map<Class<?>, ParserType<?>> parserMap = new HashMap<>();
    private final Map<MessageKey, Function<CommandContext, String>> messageResolvers = new HashMap<>();
    private final Map<Class<? extends ParserType<?>>, Function<CommandContext, String>> parserMessageResolvers = new HashMap<>();

    private Function<CommandContext, String> defaultResolver = (context) -> {
        if (context.getCurrentUsage().length() == 0) return "Incorrect usage";
        else return "Incorrect usage: " + context.getCurrentUsage();
    };

    public CommandManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("unchecked")
    protected <T> T parse(Class<T> type, Deque<String> args) throws ParsingException {
        return (T) parserMap.get(type).parse(args);
    }

    protected String getMessage(MessageKey key, CommandContext context) {
        return messageResolvers.getOrDefault(key, defaultResolver).apply(context);
    }

    protected String getMessage(Class<? extends ParserType<?>> clazz, CommandContext context) {
        return parserMessageResolvers.getOrDefault(clazz, defaultResolver).apply(context);
    }

    /**
     * A function to set the default message resolver for when an error message
     * does not have one explicitly set using {@link #setMessageResolver(Class, Function)} or {@link #setMessageResolver(MessageKey, Function)}
     * It is smart to use {@link CommandContext#getCurrentUsage()} in the resolving function
     *
     * @param function The function that resolves the default error message
     */
    public void setDefaultResolver(Function<CommandContext, String> function) {
        defaultResolver = function;
    }

    /**
     * Function used to register a custom {@link ParserType}
     *
     * @param clazz The class of the type that the parser parses
     * @param parser An instance of the parser
     * @param <T> The type that the parser is for
     */
    public <T> void registerParser(Class<T> clazz, ParserType<T> parser) {
        parserMap.put(clazz, parser);
    }

    /**
     * Method used to provide a function that dynamically computes an error message for a specific
     * {@link MessageKey}, these functions are used by methods in {@link CommandContext} to resolve
     * error messages
     *
     * @param key The message key that the resolver function is for
     * @param func The function that resolves an error message based on a {@link CommandContext}
     */
    public void setMessageResolver(MessageKey key, Function<CommandContext, String> func) {
        messageResolvers.put(key, func);
    }

    /**
     * Registers a custom error message resolver for when a specific parsing type throws a {@link ParsingException},
     * if one is not registered the default one will be used from {@link #setDefaultResolver(Function)}
     *
     * @param clazz The class of the {@link ParserType} that the resolver is being set for
     * @param func The function that resolves the message from the provided {@link CommandContext}
     */
    public void setMessageResolver(Class<? extends ParserType<?>> clazz, Function<CommandContext, String> func) {
        parserMessageResolvers.put(clazz, func);
    }

    /**
     * Registers a command through command manager instance to the underlying command system
     * 
     * @param name The name of the command (in plugin.yml)
     * @param command The executor of the command
     */
    public void registerCommand(String name, ExtendedExecutor command) {
        PluginCommand c = plugin.getCommand(name);
        if (c == null) plugin.getLogger().severe("Attempted to register a command that isnt in the plugin.yml!");
        else c.setExecutor(new BukkitCommandAdapter(this, command));
    }

    /**
     * Registers all of the parsers that come packaged with the library in {@link lol.pyr.extendedcommands.parsers}
     * to the command manager that it is executed on
     */
    public void registerDefaultParsers() {
        registerParser(Player.class, new PlayerParser());
        registerParser(World.class, new WorldParser());
        registerParser(GameMode.class, new GameModeParser());
        registerParser(Material.class, new MaterialParser());

        registerParser(Short.class, new ShortParser());
        registerParser(Integer.class, new IntegerParser());
        registerParser(Long.class, new LongParser());

        registerParser(Float.class, new FloatParser());
        registerParser(Double.class, new DoubleParser());

        registerParser(Boolean.class, new BooleanParser());
    }
}
