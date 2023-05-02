package lol.pyr.director.plugin;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.spigot.command.SpigotCommandContext;
import lol.pyr.director.spigot.command.SpigotCommandHandler;

import java.util.Collections;
import java.util.List;

public class TestCommand implements SpigotCommandHandler {
    @Override
    public void run(SpigotCommandContext context) {
        context.send("Hello, World!");
    }

    @Override
    public List<String> suggest(SpigotCommandContext context) throws CommandExecutionException {
        if (context.matchSuggestion()) return context.suggestPlayers();
        if (context.matchSuggestion("*")) return context.suggestLiteral("hi", "test1", "test2");
        return Collections.emptyList();
    }
}
