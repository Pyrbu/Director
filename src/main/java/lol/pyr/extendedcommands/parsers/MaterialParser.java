package lol.pyr.extendedcommands.parsers;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;
import org.bukkit.Material;

import java.util.Stack;

public class MaterialParser implements ParserType<Material> {
    @Override
    public Material parse(Stack<String> args) throws ParsingException {
        Material mat = Material.getMaterial(args.pop());
        if (mat == null) throw new ParsingException(getClass());
        return mat;
    }
}
