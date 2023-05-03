package lol.pyr.director.plugin;

import lol.pyr.director.spigot.command.CommandManager;
import lol.pyr.director.spigot.parse.primitive.DoubleParser;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("ALL")
public class DirectorPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        CommandManager manager = new CommandManager(this, context -> context.send(ChatColor.RED + "Incorrect usage!"));
        manager.registerParser(Double.class, new DoubleParser(context -> context.send("Incorrect usage")));
        manager.registerCommand("test", new TestCommand());
        manager.registerCommand("echo", new EchoCommand());
        manager.registerCommand("sum", new SumCommand());
    }
}
