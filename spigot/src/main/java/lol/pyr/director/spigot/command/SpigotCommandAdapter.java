package lol.pyr.director.spigot.command;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.util.ListUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;

public class SpigotCommandAdapter implements TabExecutor {
    private final SpigotCommandManager manager;
    private final SpigotCommandHandler handler;

    public SpigotCommandAdapter(SpigotCommandManager manager, SpigotCommandHandler handler) {
        this.manager = manager;
        this.handler = handler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        SpigotCommandContext context = new SpigotCommandContext(manager, sender, label, new ArrayDeque<>(ListUtil.immutableList(args)));
        try {
            handler.run(context);
        } catch (CommandExecutionException exception) {
            Message<CommandSender> msg = context.getLastMessage();
            if (msg == null) msg = manager.getDefaultMessage();
            msg.send(context);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        try {
            return handler.suggest(new SpigotCommandContext(manager, sender, label, new ArrayDeque<>(ListUtil.immutableList(args))));
        } catch (Throwable ignored) {}
        return Collections.emptyList();
    }
}
