package lol.pyr.extendedcommands.parsers;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Stack;

public class PlayerParser implements ParserType<Player> {

    @Override
    public Player parse(Stack<String> args) throws ParsingException {
        Player player = Bukkit.getPlayer(args.pop());
        if (player == null) throw new ParsingException(getClass());
        return player;
    }

}
