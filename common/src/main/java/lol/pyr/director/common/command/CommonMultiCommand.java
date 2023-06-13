package lol.pyr.director.common.command;

import lol.pyr.director.common.message.Message;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonMultiCommand<Self extends CommonMultiCommand<Self, Sender, Context, Handler>, Sender, Context extends CommonCommandContext<?, Context, Sender, Handler>, Handler extends CommonCommandHandler<Context>> implements CommonCommandHandler<Context> {
    private final Map<String, Handler> subcommands = new HashMap<>();
    private final Message<Context> helpMessage;

    public CommonMultiCommand(Message<Context> helpMessage) {
        this.helpMessage = helpMessage;
    }

    @Override
    public void run(Context context) throws CommandExecutionException {
        context.setLastMessage(helpMessage);
        if (context.argSize() < 1) context.halt();
        String arg = context.popString().toLowerCase();
        if (!subcommands.containsKey(arg)) context.halt();
        subcommands.get(arg).run(context);
    }

    @Override
    public List<String> suggest(Context context) throws CommandExecutionException {
        if (context.matchSuggestion()) return context.suggestCollection(subcommands.keySet());
        String arg = context.popString().toLowerCase();
        if (!subcommands.containsKey(arg.toLowerCase())) return Collections.emptyList();
        return subcommands.get(arg).suggest(context);
    }

    @SuppressWarnings({"unchecked", "unused"})
    public Self addSubcommand(String name, Handler executor) {
        subcommands.put(name.toLowerCase(), executor);
        return (Self) this;
    }
}
