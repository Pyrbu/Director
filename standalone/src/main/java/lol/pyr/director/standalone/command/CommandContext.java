package lol.pyr.director.standalone.command;

import lol.pyr.director.common.command.CommonCommandContext;
import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.standalone.message.DirectorReceiver;

import java.util.Deque;

@SuppressWarnings("unused")
public class CommandContext extends CommonCommandContext<CommandManager, CommandContext, DirectorReceiver, CommandHandler> {
    public CommandContext(CommandManager manager, DirectorReceiver sender, String label, Deque<String> args) {
        super(manager, sender, label, args);
    }

    public void halt(String message) throws CommandExecutionException {
        setLastMessage(r -> r.getSender().send(message));
        throw new CommandExecutionException();
    }

    public void send(String message) {
        getSender().send(message);
    }
}
