package lol.pyr.director.standalone.command;

import lol.pyr.director.common.command.CommandContext;
import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.command.CommandManager;
import lol.pyr.director.standalone.message.DirectorReceiver;

import java.util.Deque;

public class StandaloneCommandContext extends CommandContext<DirectorReceiver> {
    public StandaloneCommandContext(CommandManager<DirectorReceiver> manager, DirectorReceiver sender, String label, Deque<String> args) {
        super(manager, sender, label, args);
    }

    public void halt(String message) throws CommandExecutionException {
        setLastMessage(r -> r.getSender().send(message));
        throw new CommandExecutionException();
    }
}
