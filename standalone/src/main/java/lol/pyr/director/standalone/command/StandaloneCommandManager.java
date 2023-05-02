package lol.pyr.director.standalone.command;

import lol.pyr.director.common.command.CommandContext;
import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.director.common.command.CommandHandler;
import lol.pyr.director.common.command.CommandManager;
import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.util.ListUtil;
import lol.pyr.director.standalone.message.DirectorReceiver;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class StandaloneCommandManager extends CommandManager<DirectorReceiver> {
    private final Map<String, StandaloneCommandHandler> commandMap = new HashMap<>();
    private Message<DirectorReceiver> unknownCommandMessage = receiver -> {};

    public StandaloneCommandManager(Message<DirectorReceiver> defaultMessage) {
        super(defaultMessage);
    }

    public void setUnknownCommandMessage(Message<DirectorReceiver> unknownCommandMessage) {
        this.unknownCommandMessage = unknownCommandMessage;
    }

    public void registerCommand(String name, StandaloneCommandHandler command) {
        commandMap.put(name.toLowerCase(), command);
    }

    @Override
    public void registerCommand(String name, CommandHandler<DirectorReceiver> command) {
        if (command instanceof StandaloneCommandHandler) registerCommand(name, (StandaloneCommandHandler) command);
        throw new UnsupportedOperationException("StandaloneCommandManager only accepts StandaloneCommandHandler");
    }

    public void runCommand(DirectorReceiver sender, String[] args) {
        if (args.length == 0) throw new IllegalStateException("Zero arguments passed to StandaloneCommandManager#runCommand()");
        String label = args[0];
        CommandContext<DirectorReceiver> context = new CommandContext<>(this, sender, label, new ArrayDeque<>(ListUtil.immutableList(Arrays.copyOfRange(args, 1, args.length))));
        if (!commandMap.containsKey(label.toLowerCase())) {
            unknownCommandMessage.send(context);
            return;
        }
        try {
            commandMap.get(label.toLowerCase()).run(context);
        } catch (CommandExecutionException exception) {
            Message<DirectorReceiver> msg = context.getLastMessage();
            if (msg == null) msg = getDefaultMessage();
            msg.send(context);
        }
    }
}
