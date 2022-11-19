package lol.pyr.extendedcommands;

import lol.pyr.extendedcommands.api.ExtendedExecutor;
import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;
import lol.pyr.extendedcommands.parsers.*;
import lol.pyr.extendedcommands.parsers.primitive.*;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;

@SuppressWarnings("unused")
public class CommandManager <P extends JavaPlugin> {
    @Getter private final P plugin;
    private final Map<Class<?>, ParserType<?>> parserMap = new HashMap<>();
    private final Map<MessageKey, Function<CommandContext<P>, String>> messageResolvers = new HashMap<>();
    private final Map<Class<? extends ParserType<?>>, Function<CommandContext<P>, String>> parserMessageResolvers = new HashMap<>();

    @Setter private Function<CommandContext<P>, String> defaultResolver = (context) -> {
        if (context.getCurrentUsage().length() == 0) return "Incorrect usage";
        else return "Incorrect usage: " + context.getCurrentUsage();
    };

    public CommandManager(P plugin) {
        this.plugin = plugin;
    }

    public <T> void registerParser(Class<T> clazz, ParserType<T> parser) {
        parserMap.put(clazz, parser);
    }

    public void setMessageResolver(MessageKey key, Function<CommandContext<P>, String> func) {
        messageResolvers.put(key, func);
    }

    public void setMessageResolver(Class<? extends ParserType<?>> clazz, Function<CommandContext<P>, String> func) {
        parserMessageResolvers.put(clazz, func);
    }

    public void registerCommand(String name, ExtendedExecutor<P> command) {
        PluginCommand c = plugin.getCommand(name);
        if (c == null) plugin.getLogger().severe("Attempted to register a command that isnt in the plugin.yml!");
        else c.setExecutor(new BukkitCommandAdapter<>(this, command));
    }

    @SuppressWarnings("unchecked")
    protected <T> T parse(Class<T> type, Stack<String> args) throws ParsingException {
        return (T) parserMap.get(type).parse(args);
    }

    protected String getMessage(MessageKey key, CommandContext<P> context) {
        return messageResolvers.getOrDefault(key, defaultResolver).apply(context);
    }

    protected String getMessage(Class<? extends ParserType<?>> clazz, CommandContext<P> context) {
        return parserMessageResolvers.getOrDefault(clazz, defaultResolver).apply(context);
    }

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
