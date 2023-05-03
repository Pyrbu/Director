package lol.pyr.director.standalone.command;

import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.command.CommandManager;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.util.ListUtil;
import lol.pyr.director.standalone.message.DirectorReceiver;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class StandaloneCommandManager extends CommandManager<DirectorReceiver, StandaloneCommandHandler, StandaloneCommandContext> {
    private final Map<String, StandaloneCommandHandler> commandMap = new HashMap<>();
    private Message<StandaloneCommandContext> unknownCommandMessage = receiver -> {};

    public StandaloneCommandManager(Message<StandaloneCommandContext> defaultMessage) {
        super(defaultMessage);
    }

    public void setUnknownCommandMessage(Message<StandaloneCommandContext> unknownCommandMessage) {
        this.unknownCommandMessage = unknownCommandMessage;
    }

    public void registerCommand(String name, StandaloneCommandHandler command) {
        commandMap.put(name.toLowerCase(), command);
    }

    public void runCommand(DirectorReceiver sender, String[] args) {
        if (args.length == 0) throw new IllegalStateException("Zero arguments passed to StandaloneCommandManager#runCommand()");
        String label = args[0];
        StandaloneCommandContext context = new StandaloneCommandContext(this, sender, label, new ArrayDeque<>(ListUtil.immutableList(Arrays.copyOfRange(args, 1, args.length))));
        if (!commandMap.containsKey(label.toLowerCase())) {
            unknownCommandMessage.send(context);
            return;
        }
        try {
            commandMap.get(label.toLowerCase()).run(context);
        } catch (CommandExecutionException exception) {
            Message<StandaloneCommandContext> msg = context.getLastMessage();
            if (msg == null) msg = getDefaultMessage();
            msg.send(context);
        }
    }
}
