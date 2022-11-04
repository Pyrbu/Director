package lol.pyr.extendedcommands.parsers;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.Stack;

public class WorldParser implements ParserType<World> {
    @Override
    public World parse(Stack<String> args) throws ParsingException {
        World world = Bukkit.getWorld(args.pop());
        if (world == null) throw new ParsingException(getClass());
        return world;
    }
}
