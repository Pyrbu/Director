package lol.pyr.director.standalone.command;

import lol.pyr.director.common.command.CommonMultiCommand;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.standalone.message.DirectorReceiver;

@SuppressWarnings("unused")
public class MultiCommand extends CommonMultiCommand<MultiCommand, DirectorReceiver, CommandContext, CommandHandler> implements CommandHandler {
    public MultiCommand(Message<CommandContext> helpMessage) {
        super(helpMessage);
    }
}
