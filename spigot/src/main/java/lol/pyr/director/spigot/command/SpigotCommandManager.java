package lol.pyr.director.spigot.command;

import lol.pyr.director.common.command.CommandManager;
import lol.pyr.director.common.command.CommandHandler;
import lol.pyr.director.common.message.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotCommandManager extends CommandManager<CommandSender> {
    private final JavaPlugin plugin;

    public SpigotCommandManager(JavaPlugin plugin, Message<CommandSender> defaultMessage) {
        super(defaultMessage);
        this.plugin = plugin;
    }

    public void registerCommand(String name, SpigotCommandHandler command) {
        PluginCommand c = plugin.getCommand(name);
        if (c == null) throw new IllegalStateException("Tried to register a command that isn't in the plugin.yml!");
        c.setExecutor(new SpigotCommandAdapter(this, command));
    }

    @Override
    public void registerCommand(String name, CommandHandler<CommandSender> command) {
        if (command instanceof SpigotCommandHandler) registerCommand(name, (SpigotCommandHandler) command);
        throw new UnsupportedOperationException("SpigotCommandManager only accepts SpigotCommandHandler");
    }
}
