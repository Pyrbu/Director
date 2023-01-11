package lol.pyr.extendedcommands.parsers;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Stack;

/**
 * An implementation of {@link ParserType} for parsing the {@link Player} class
 */
public class PlayerParser implements ParserType<Player> {
    /**
     * Parses a stack of strings into a {@link Player} using the {@link Bukkit#getPlayer(String)} method
     *
     * @param args The arguments that are provided for parsing
     * @return A parsed {@link Player}
     * @throws ParsingException When parsing could not be completed
     */
    @Override
    public Player parse(Stack<String> args) throws ParsingException {
        Player player = Bukkit.getPlayer(args.pop());
        if (player == null) throw new ParsingException(getClass());
        return player;
    }
}
