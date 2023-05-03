package lol.pyr.director.adventure.parse.spigot;

import lol.pyr.director.adventure.command.CommandContext;
import lol.pyr.director.adventure.parse.ParserType;
import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.Deque;

@SuppressWarnings("unused")
public class WorldParser extends ParserType<World> {
    public WorldParser(Message<CommandContext> message) {
        super(message);
    }

    @Override
    public World parse(Deque<String> args) throws CommandExecutionException {
        World world = Bukkit.getWorld(args.pop());
        if (world == null) throw new CommandExecutionException();
        return world;
    }
}
