package lol.pyr.director.paper.command;

import lol.pyr.director.common.command.CommonMultiCommand;
import lol.pyr.director.common.message.Message;
import org.bukkit.command.CommandSender;

@SuppressWarnings("unused")
public class MultiCommand extends CommonMultiCommand<MultiCommand, CommandSender, CommandContext, CommandHandler> implements CommandHandler {
    public MultiCommand(Message<CommandContext> helpMessage) {
        super(helpMessage);
    }
}
