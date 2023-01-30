package lol.pyr.extendedcommands.multicommands;

import lol.pyr.extendedcommands.CommandContext;
import lol.pyr.extendedcommands.api.HelpPrintable;

import java.util.function.Function;

@SuppressWarnings("unused")
public class HelpPrintableMultiCommand extends MultiCommand implements HelpPrintable {
    private final int linePriority;

    public HelpPrintableMultiCommand(int linePriority, Function<CommandContext, String> helpMessageResolver) {
        super(helpMessageResolver);
        this.linePriority = linePriority;
    }
    public HelpPrintableMultiCommand(int linePriority) {
        super();
        this.linePriority = linePriority;
    }

    @Override
    public String getLine(CommandContext context) {
        return helpMessageResolver.apply(context);
    }

    @Override
    public int getLinePriority(CommandContext context) {
        return linePriority;
    }
}
