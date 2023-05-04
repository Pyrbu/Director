package lol.pyr.director.spigot.parse.paper;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.spigot.command.CommandContext;
import lol.pyr.director.spigot.parse.ParserType;
import org.bukkit.GameMode;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class GameModeParser extends ParserType<GameMode> {
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

    public GameModeParser(Message<CommandContext> message) {
        super(message);
    }

    @Override
    public GameMode parse(Deque<String> args) throws CommandExecutionException {
        String arg = args.pop().toLowerCase();
        if (!aliasMap.containsKey(arg)) throw new CommandExecutionException();
        return aliasMap.get(arg);
    }
}
