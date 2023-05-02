package lol.pyr.director.plugin;

import lol.pyr.director.spigot.command.SpigotCommandManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("ALL")
public class DirectorPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        SpigotCommandManager manager = new SpigotCommandManager(this, context -> context.getSender().sendMessage(ChatColor.RED + "Incorrect usage!"));
        manager.registerCommand("test", new TestCommand());
    }
}
