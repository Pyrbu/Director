package lol.pyr.extendedcommands.multicommands;

import lol.pyr.extendedcommands.CommandContext;
import lol.pyr.extendedcommands.api.ExtendedExecutor;
import lol.pyr.extendedcommands.api.HelpPrintable;
import lol.pyr.extendedcommands.exception.CommandExecutionException;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unused")
public class MultiCommand implements ExtendedExecutor, HelpPrintable {
    private final Map<String, ExtendedExecutor> subcommands = new HashMap<>();
    protected final Function<CommandContext, Component> helpMessageResolver;
    private final int linePriority;

    public MultiCommand(Function<CommandContext, Component> helpMessageResolver) {
        this(0, helpMessageResolver);
    }

    public MultiCommand() {
        this(0);
    }

    public MultiCommand(int linePriority, Function<CommandContext, Component> helpMessageResolver) {
        this.linePriority = linePriority;
        this.helpMessageResolver = helpMessageResolver;
    }

    public MultiCommand(int linePriority) {
        this.linePriority = linePriority;
        this.helpMessageResolver = context -> {
            TextComponent.Builder component = Component.text();
            subcommands.values().stream()
                    .filter(executor -> executor instanceof HelpPrintable)
                    .map(executor -> (HelpPrintable) executor)
                    .sorted(Comparator.comparingInt(printable -> printable.getLinePriority(context)))
                    .map(printable -> printable.getLine(context))
                    .forEach(component::append);
            return component.build();
        };
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

    @Override
    public Component getLine(CommandContext context) {
        return helpMessageResolver.apply(context);
    }

    @Override
    public int getLinePriority(CommandContext context) {
        return linePriority;
    }
}
