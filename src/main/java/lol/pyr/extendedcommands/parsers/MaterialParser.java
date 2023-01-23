package lol.pyr.extendedcommands.parsers;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;
import org.bukkit.Material;

import java.util.Deque;

/**
 * An implementation of {@link ParserType} for parsing the {@link Material} class
 */
public class MaterialParser implements ParserType<Material> {
    /**
     * Parses a stack of strings into a {@link Material} using the {@link Material#getMaterial(String)} method
     *
     * @param args The arguments that are provided for parsing
     * @return A parsed {@link Material}
     * @throws ParsingException When parsing could not be completed
     */
    @Override
    public Material parse(Deque<String> args) throws ParsingException {
        Material mat = Material.getMaterial(args.pop());
        if (mat == null) throw new ParsingException(getClass());
        return mat;
    }
}
