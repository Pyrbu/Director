package lol.pyr.extendedcommands.api;

import lol.pyr.extendedcommands.CommandContext;
import lol.pyr.extendedcommands.multicommands.MultiCommand;

/**
 * This class can be implemented to mark a class as printable
 * in /help commands, this is currently only used in {@link MultiCommand}
 */
public interface HelpPrintable {
    /**
     * A function to get the string to be printed by this class
     * @param context The current command context
     * @return The string to be printed
     */
    String getLine(CommandContext context);

    /**
     * The sorting priority of this line, higher priority goes first
     * @param context The current command context
     * @return The sorting priority
     */
    int getLinePriority(CommandContext context);
}
