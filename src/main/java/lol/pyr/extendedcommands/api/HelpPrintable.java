package lol.pyr.extendedcommands.api;

/**
 * This class can be implemented to mark a class as printable
 * in /help commands, this is currently only used in {@link lol.pyr.extendedcommands.MultiCommand}
 */
public interface HelpPrintable {
    /**
     * A function to get the string to be printed by this class
     * @return The string to be printed
     */
    String getLine();

    /**
     * The sorting priority of this line, higher priority goes first
     * @return The sorting priority
     */
    int getLinePriority();
}
