package lol.pyr.director.common.command;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.message.PrioritizedMessage;

import java.util.*;

@SuppressWarnings("unused")
public class MultiCommand<Sender, Context extends CommonCommandContext<?, ?, Sender, ?>> implements CommonCommandHandler<Context>, PrioritizedMessage<Context> {
    private final Map<String, CommonCommandHandler<Context>> subcommands = new HashMap<>();
    private final List<PrioritizedMessage<Context>> pMessages = new ArrayList<>();
    private final List<Message<Context>> messages = new ArrayList<>();
    private final int linePriority;

    public MultiCommand(int linePriority) {
        this.linePriority = linePriority;
    }

    @Override
    public void run(Context context) throws CommandExecutionException {
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

    public MultiCommand<Sender, Context> addSubcommand(String name, CommonCommandHandler<Context> executor) {
        subcommands.put(name.toLowerCase(), executor);
        return this;
    }

    @SuppressWarnings("unchecked")
    private void resortMessages() {
        pMessages.clear();
        messages.clear();
        for (CommonCommandHandler<Context> handler : subcommands.values()) {
            if (handler instanceof PrioritizedMessage) pMessages.add((PrioritizedMessage<Context>) handler);
            else if (handler instanceof Message) messages.add((Message<Context>) handler);
        }
        pMessages.sort(Comparator.comparingInt(PrioritizedMessage::getPriority));
    }

    @Override
    public void send(Context context) {
        for (PrioritizedMessage<Context> msg : pMessages) msg.send(context);
        for (Message<Context> msg : messages) msg.send(context);
    }

    @Override
    public int getPriority() {
        return linePriority;
    }
}
