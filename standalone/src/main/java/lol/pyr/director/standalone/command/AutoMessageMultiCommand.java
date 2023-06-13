package lol.pyr.director.standalone.command;

import lol.pyr.director.common.command.CommonAutoMessageMultiCommand;
import lol.pyr.director.standalone.message.DirectorReceiver;

@SuppressWarnings("unused")
public class AutoMessageMultiCommand extends CommonAutoMessageMultiCommand<AutoMessageMultiCommand, DirectorReceiver, CommandContext, CommandHandler> implements CommandHandler {
    public AutoMessageMultiCommand() {
        this(0);
    }

    public AutoMessageMultiCommand(int linePriority) {
        super(linePriority);
    }
}
