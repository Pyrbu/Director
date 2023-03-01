package lol.pyr.extendedcommands.multicommands;

import lol.pyr.extendedcommands.CommandContext;
import lol.pyr.extendedcommands.api.HelpPrintable;
import net.kyori.adventure.text.Component;

import java.util.function.Function;

@SuppressWarnings("unused")
public class HelpPrintableMultiCommand extends MultiCommand implements HelpPrintable {
    private final int linePriority;

    public HelpPrintableMultiCommand(int linePriority, Function<CommandContext, Component> helpMessageResolver) {
        super(helpMessageResolver);
        this.linePriority = linePriority;
    }
    public HelpPrintableMultiCommand(int linePriority) {
        super();
        this.linePriority = linePriority;
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
