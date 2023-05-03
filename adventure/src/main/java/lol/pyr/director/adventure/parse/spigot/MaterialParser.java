package lol.pyr.director.adventure.parse.spigot;

import lol.pyr.director.adventure.command.CommandContext;
import lol.pyr.director.adventure.parse.ParserType;
import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.message.Message;
import org.bukkit.Material;

import java.util.Deque;

@SuppressWarnings("unused")
public class MaterialParser extends ParserType<Material> {
    public MaterialParser(Message<CommandContext> message) {
        super(message);
    }

    @Override
    public Material parse(Deque<String> args) throws CommandExecutionException {
        Material mat = Material.getMaterial(args.pop());
        if (mat == null) throw new CommandExecutionException();
        return mat;
    }
}
