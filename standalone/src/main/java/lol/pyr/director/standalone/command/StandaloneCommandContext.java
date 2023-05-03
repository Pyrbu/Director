package lol.pyr.director.standalone.command;

import lol.pyr.director.common.command.CommonCommandContext;
import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.standalone.message.DirectorReceiver;

import java.util.Deque;

@SuppressWarnings("unused")
public class StandaloneCommandContext extends CommonCommandContext<StandaloneCommandManager, StandaloneCommandContext, DirectorReceiver, StandaloneCommandHandler> {
    public StandaloneCommandContext(StandaloneCommandManager manager, DirectorReceiver sender, String label, Deque<String> args) {
        super(manager, sender, label, args);
    }

    public void halt(String message) throws CommandExecutionException {
        setLastMessage(r -> r.getSender().send(message));
        throw new CommandExecutionException();
    }
}
