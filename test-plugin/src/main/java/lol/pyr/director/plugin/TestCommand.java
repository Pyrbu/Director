package lol.pyr.director.plugin;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.spigot.command.CommandContext;
import lol.pyr.director.spigot.command.CommandHandler;

import java.util.Collections;
import java.util.List;

public class TestCommand implements CommandHandler {
    @Override
    public void run(CommandContext context) {
        context.send("Hello, World!");
    }

    @Override
    public List<String> suggest(CommandContext context) throws CommandExecutionException {
        if (context.matchSuggestion()) return context.suggestPlayers();
        if (context.matchSuggestion("*")) return context.suggestLiteral("hi", "test1", "test2");
        return Collections.emptyList();
    }
}
