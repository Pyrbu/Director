package lol.pyr.director.spigot.command;

import lol.pyr.director.common.command.CommonAutoMessageMultiCommand;
import org.bukkit.command.CommandSender;

@SuppressWarnings("unused")
public class AutoMessageMultiCommand extends CommonAutoMessageMultiCommand<AutoMessageMultiCommand, CommandSender, CommandContext, CommandHandler> implements CommandHandler {
    public AutoMessageMultiCommand() {
        this(0);
    }

    public AutoMessageMultiCommand(int linePriority) {
        super(linePriority);
    }
}
