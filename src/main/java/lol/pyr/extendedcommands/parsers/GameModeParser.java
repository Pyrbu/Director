package lol.pyr.extendedcommands.parsers;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;
import org.bukkit.GameMode;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of {@link ParserType} for parsing the {@link GameMode} class
 */
public class GameModeParser implements ParserType<GameMode> {
    private final static Map<String, GameMode> aliasMap = new HashMap<>();
    static {
        for (GameMode gm : GameMode.values()) aliasMap.put(gm.name().toLowerCase(), gm);
        aliasMap.put("0", GameMode.SURVIVAL);
        aliasMap.put("s", GameMode.SURVIVAL);

        aliasMap.put("1", GameMode.CREATIVE);
        aliasMap.put("c", GameMode.CREATIVE);

        aliasMap.put("2", GameMode.ADVENTURE);
        aliasMap.put("a", GameMode.ADVENTURE);

        aliasMap.put("3", GameMode.SPECTATOR);
        aliasMap.put("sp", GameMode.SPECTATOR);
    }

    /**
     * Parses a stack of strings into a {@link GameMode}, also parses common
     * aliases like "0", "s", "1", etc
     *
     * @param args The arguments that are provided for parsing
     * @return A parsed {@link GameMode}
     * @throws ParsingException When parsing could not be completed
     */
    @Override
    public GameMode parse(Deque<String> args) throws ParsingException {
        String arg = args.pop().toLowerCase();
        if (!aliasMap.containsKey(arg)) throw new ParsingException(getClass());
        return aliasMap.get(arg);
    }
}
