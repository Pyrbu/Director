package lol.pyr.director.plugin;

import lol.pyr.director.spigot.command.SpigotCommandManager;
import lol.pyr.director.spigot.parse.primitive.SpigotDoubleParser;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("ALL")
public class DirectorPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        SpigotCommandManager manager = new SpigotCommandManager(this, context -> context.send(ChatColor.RED + "Incorrect usage!"));
        manager.registerParser(Double.class, new SpigotDoubleParser(context -> context.send("Incorrect usage")));
        manager.registerCommand("test", new TestCommand());
        manager.registerCommand("echo", new EchoCommand());
        manager.registerCommand("sum", new SumCommand());
    }
}
