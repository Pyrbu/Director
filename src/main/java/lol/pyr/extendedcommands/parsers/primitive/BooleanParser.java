package lol.pyr.extendedcommands.parsers.primitive;

import lol.pyr.extendedcommands.api.ParserType;
import lol.pyr.extendedcommands.exception.ParsingException;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BooleanParser implements ParserType<Boolean> {

    private final static Map<String, Boolean> aliasMap = new HashMap<>();
    static {
        aliasMap.put("true", true);
        aliasMap.put("false", false);

        aliasMap.put("1", true);
        aliasMap.put("0", false);

        aliasMap.put("yes", true);
        aliasMap.put("no", false);

        aliasMap.put("enable", true);
        aliasMap.put("enabled", true);
        aliasMap.put("disable", false);
        aliasMap.put("disabled", false);
    }

    @Override
    public Boolean parse(Stack<String> args) throws ParsingException {
        String arg = args.pop().toLowerCase();
        if (!aliasMap.containsKey(arg)) throw new ParsingException(getClass());
        return aliasMap.get(arg);
    }
}
