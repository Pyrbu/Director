package lol.pyr.extendedcommands.parsers;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.Stack;

/**
 * An implementation of {@link ParserType} for parsing the {@link World} class
 */
public class WorldParser implements ParserType<World> {
    /**
     * Parses a stack of strings into a {@link World} using the {@link Bukkit#getWorld(String)} method
     *
     * @param args The arguments that are provided for parsing
     * @return A parsed {@link World}
     * @throws ParsingException When parsing could not be completed
     */
    @Override
    public World parse(Stack<String> args) throws ParsingException {
        World world = Bukkit.getWorld(args.pop());
        if (world == null) throw new ParsingException(getClass());
        return world;
    }
}
