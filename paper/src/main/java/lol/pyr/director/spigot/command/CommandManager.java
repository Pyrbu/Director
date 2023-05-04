package lol.pyr.director.spigot.command;

import lol.pyr.director.common.command.CommonCommandManager;
import lol.pyr.director.common.message.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager extends CommonCommandManager<CommandSender, CommandHandler, CommandContext> {
    private final JavaPlugin plugin;

    public CommandManager(JavaPlugin plugin, Message<CommandContext> defaultMessage) {
        super(defaultMessage);
        this.plugin = plugin;
    }

    public void registerCommand(String name, CommandHandler command) {
        PluginCommand c = plugin.getCommand(name);
        if (c == null) throw new IllegalStateException("Tried to register a command that isn't in the plugin.yml!");
        c.setExecutor(new CommandAdapter(this, command));
    }
}
