package lol.pyr.director.plugin;

import lol.pyr.director.spigot.command.SpigotCommandContext;
import lol.pyr.director.spigot.command.SpigotCommandHandler;

import java.util.Collections;
import java.util.List;

public class EchoCommand implements SpigotCommandHandler {
    @Override
    public void run(SpigotCommandContext context) {
        context.send(context.dumpAllArgs());
    }

    @Override
    public List<String> suggest(SpigotCommandContext context) {
        return Collections.emptyList();
    }
}
