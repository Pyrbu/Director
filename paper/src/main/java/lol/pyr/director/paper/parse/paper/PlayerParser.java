package lol.pyr.director.paper.parse.paper;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.paper.command.CommandContext;
import lol.pyr.director.paper.parse.ParserType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Deque;

@SuppressWarnings("unused")
public class PlayerParser extends ParserType<Player> {
    public PlayerParser(Message<CommandContext> message) {
        super(message);
    }

    @Override
    public Player parse(Deque<String> args) throws CommandExecutionException {
        Player player = Bukkit.getPlayer(args.pop());
        if (player == null) throw new CommandExecutionException();
        return player;
    }
}
