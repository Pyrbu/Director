package lol.pyr.director.spigot.parse.spigot;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.spigot.parse.SpigotParser;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;

import java.util.Deque;

public class MaterialParser extends SpigotParser<Material> {
    public MaterialParser(Message<CommandSender> message) {
        super(message);
    }

    @Override
    public Material parse(Deque<String> args) throws CommandExecutionException {
        Material mat = Material.getMaterial(args.pop());
        if (mat == null) throw new CommandExecutionException();
        return mat;
    }
}
