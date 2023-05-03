package lol.pyr.director.plugin;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.spigot.command.CommandContext;
import lol.pyr.director.spigot.command.CommandHandler;

import java.util.Collections;
import java.util.List;

public class SumCommand implements CommandHandler {
    @Override
    public void run(CommandContext context) throws CommandExecutionException {
        double sum = 0;
        while (context.argSize() > 0) {
            sum += context.parse(Double.class);
        }
        context.send("The sum is: " + sum);
    }

    @Override
    public List<String> suggest(CommandContext context) {
        return Collections.emptyList();
    }
}
