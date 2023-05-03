package lol.pyr.director.adventure.command;

import lol.pyr.director.common.command.CommonCommandManager;
import lol.pyr.director.common.message.Message;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager extends CommonCommandManager<CommandSender, CommandHandler, CommandContext> {
    private final JavaPlugin plugin;
    private final BukkitAudiences audiences;

    public CommandManager(JavaPlugin plugin, BukkitAudiences audiences, Message<CommandContext> defaultMessage) {
        super(defaultMessage);
        this.plugin = plugin;
        this.audiences = audiences;
    }

    public void registerCommand(String name, CommandHandler command) {
        PluginCommand c = plugin.getCommand(name);
        if (c == null) throw new IllegalStateException("Tried to register a command that isn't in the plugin.yml!");
        c.setExecutor(new CommandAdapter(this, command));
    }

    protected BukkitAudiences getAudiences() {
        return audiences;
    }
}
