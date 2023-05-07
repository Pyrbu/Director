package lol.pyr.director.adventure.command;

import lol.pyr.director.common.command.CommonMultiCommand;
import org.bukkit.command.CommandSender;

@SuppressWarnings("unused")
public class MultiCommand extends CommonMultiCommand<MultiCommand, CommandSender, CommandContext, CommandHandler> implements CommandHandler {
    public MultiCommand() {
        this(0);
    }

    public MultiCommand(int linePriority) {
        super(linePriority);
    }
}
