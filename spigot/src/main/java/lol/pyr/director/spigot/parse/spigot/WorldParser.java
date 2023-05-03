package lol.pyr.director.spigot.parse.spigot;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.spigot.command.SpigotCommandContext;
import lol.pyr.director.spigot.parse.SpigotParser;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.Deque;

@SuppressWarnings("unused")
public class WorldParser extends SpigotParser<World> {
    public WorldParser(Message<SpigotCommandContext> message) {
        super(message);
    }

    @Override
    public World parse(Deque<String> args) throws CommandExecutionException {
        World world = Bukkit.getWorld(args.pop());
        if (world == null) throw new CommandExecutionException();
        return world;
    }
}
