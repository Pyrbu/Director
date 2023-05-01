package lol.pyr.director.standalone.command;

import lol.pyr.director.common.command.CommandContext;
import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.command.CommandHandler;
import lol.pyr.director.common.command.CommandManager;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.util.ListUtil;
import lol.pyr.director.standalone.message.DirectorReceiver;

import java.util.ArrayDeque;

public class StandaloneCommandManager extends CommandManager<DirectorReceiver> {


    public StandaloneCommandManager(Message<DirectorReceiver> defaultMessage) {
        super(defaultMessage);
    }

    @Override
    public void registerCommand(String name, CommandHandler<DirectorReceiver> command) {

    }

    public void runCommand(String[] args) {
        CommandContext<DirectorReceiver> context = new CommandContext<>(manager, sender, label, new ArrayDeque<>(ListUtil.immutableList(args)));
        try {
            handler.run(context);
        } catch (CommandExecutionException exception) {
            Message<CommandSender> msg = context.getLastMessage();
            if (msg == null) msg = manager.getDefaultMessage();
            msg.send(context);
        }
        return true;
    }
}
