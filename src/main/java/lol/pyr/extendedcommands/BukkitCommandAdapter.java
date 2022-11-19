package lol.pyr.extendedcommands;

import lol.pyr.extendedcommands.api.ExtendedExecutor;
import lol.pyr.extendedcommands.exception.CommandExecutionException;
import lol.pyr.extendedcommands.util.StackUtil;
import lombok.AllArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@AllArgsConstructor
public class BukkitCommandAdapter <P extends JavaPlugin> implements TabExecutor {
    private CommandManager<P> manager;
    private ExtendedExecutor<P> executor;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            CommandContext<P> context = new CommandContext<>(manager, sender, command, label, StackUtil.fromArray(args));
            executor.run(context);
        } catch (CommandExecutionException exception) {
            sender.sendMessage(exception.getMessage());
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        try {
            return executor.complete(new CommandContext<>(manager, sender, command, label, StackUtil.fromArray(args, false)));
        } catch (Throwable ignored) {}
        return List.of();
    }
}
