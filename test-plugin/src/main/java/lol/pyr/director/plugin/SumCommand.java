package lol.pyr.director.plugin;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.spigot.command.SpigotCommandContext;
import lol.pyr.director.spigot.command.SpigotCommandHandler;

import java.util.List;

public class SumCommand implements SpigotCommandHandler {
    @Override
    public void run(SpigotCommandContext context) throws CommandExecutionException {
        double sum = 0;
        while (context.argSize() > 0) {
            sum += context.parse(Double.class);
        }
        context.send("The sum is: " + sum);
    }

    @Override
    public List<String> suggest(SpigotCommandContext context) throws CommandExecutionException {
        return SpigotCommandHandler.super.suggest(context);
    }
}
