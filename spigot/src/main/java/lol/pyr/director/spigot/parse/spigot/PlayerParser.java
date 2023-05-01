package lol.pyr.director.spigot.parse.spigot;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.spigot.parse.SpigotParser;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Deque;

public class PlayerParser extends SpigotParser<Player> {
    public PlayerParser(Message<CommandSender> message) {
        super(message);
    }

    @Override
    public Player parse(Deque<String> args) throws CommandExecutionException {
        Player player = Bukkit.getPlayer(args.pop());
        if (player == null) throw new CommandExecutionException();
        return player;
    }
}
