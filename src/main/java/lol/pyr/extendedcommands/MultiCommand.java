package lol.pyr.extendedcommands;

import lol.pyr.extendedcommands.api.ExtendedExecutor;
import lol.pyr.extendedcommands.exception.CommandExecutionException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unused")
public class MultiCommand implements ExtendedExecutor {
    private final Map<String, ExtendedExecutor> subcommands = new HashMap<>();
    private final Function<CommandContext, String> helpMessageResolver;

    public MultiCommand(Function<CommandContext, String> helpMessageResolver) {
        this.helpMessageResolver = helpMessageResolver;
    }

    @Override
    public void run(CommandContext context) throws CommandExecutionException {
        if (context.argSize() < 1) context.halt(helpMessageResolver.apply(context));
        String arg = context.popString().toLowerCase();
        if (!subcommands.containsKey(arg)) context.halt(helpMessageResolver.apply(context));
        subcommands.get(arg).run(context);
    }

    @Override
    public List<String> complete(CommandContext context) throws CommandExecutionException {
        if (context.matchCompletion()) return context.completeCollection(subcommands.keySet());
        String arg = context.popString().toLowerCase();
        if (!subcommands.containsKey(arg.toLowerCase())) return List.of();
        return subcommands.get(arg).complete(context);
    }

    public MultiCommand addSubcommand(String name, ExtendedExecutor executor) {
        subcommands.put(name.toLowerCase(), executor);
        return this;
    }
}
