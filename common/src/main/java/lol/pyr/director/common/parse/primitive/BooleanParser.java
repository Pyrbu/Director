package lol.pyr.director.common.parse.primitive;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.parse.ParserType;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public abstract class BooleanParser<S> implements ParserType<Boolean, S> {
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
    public Boolean parse(Deque<String> args) throws CommandExecutionException {
        String arg = args.pop().toLowerCase();
        if (!aliasMap.containsKey(arg)) throw new CommandExecutionException();
        return aliasMap.get(arg);
    }
}
