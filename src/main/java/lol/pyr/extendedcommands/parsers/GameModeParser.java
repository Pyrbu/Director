package lol.pyr.extendedcommands.parsers;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;
import org.bukkit.GameMode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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

    @Override
    public GameMode parse(Stack<String> args) throws ParsingException {
        String arg = args.pop().toLowerCase();
        if (!aliasMap.containsKey(arg)) throw new ParsingException(getClass());
        return aliasMap.get(arg);
    }

}
