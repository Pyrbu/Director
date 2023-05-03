package lol.pyr.director.plugin;

import lol.pyr.director.spigot.command.CommandContext;
import lol.pyr.director.spigot.command.CommandHandler;

import java.util.Collections;
import java.util.List;

public class EchoCommand implements CommandHandler {
    @Override
    public void run(CommandContext context) {
        context.send(context.dumpAllArgs());
    }

    @Override
    public List<String> suggest(CommandContext context) {
        return Collections.emptyList();
    }
}
