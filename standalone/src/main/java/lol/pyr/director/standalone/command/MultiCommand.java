package lol.pyr.director.standalone.command;

import lol.pyr.director.common.command.CommonMultiCommand;
import lol.pyr.director.standalone.message.DirectorReceiver;

@SuppressWarnings("unused")
public class MultiCommand extends CommonMultiCommand<DirectorReceiver, CommandContext, CommandHandler> {
    public MultiCommand() {
        this(0);
    }

    public MultiCommand(int linePriority) {
        super(linePriority);
    }
}
