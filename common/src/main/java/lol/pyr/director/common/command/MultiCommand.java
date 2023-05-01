package lol.pyr.director.common.command;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.message.PrioritizedMessage;

import java.util.*;

@SuppressWarnings("unused")
public class MultiCommand<S> implements CommandHandler<S>, PrioritizedMessage<S> {
    private final Map<String, CommandHandler<S>> subcommands = new HashMap<>();
    private final List<PrioritizedMessage<S>> pMessages = new ArrayList<>();
    private final List<Message<S>> messages = new ArrayList<>();
    private final int linePriority;

    public MultiCommand(int linePriority) {
        this.linePriority = linePriority;
    }

    @Override
    public void run(CommandContext<S> context) throws CommandExecutionException {
        if (context.argSize() < 1) context.halt();
        String arg = context.popString().toLowerCase();
        if (!subcommands.containsKey(arg)) context.halt();
        subcommands.get(arg).run(context);
    }

    @Override
    public List<String> suggest(CommandContext<S> context) throws CommandExecutionException {
        if (context.matchSuggestion()) return context.suggestCollection(subcommands.keySet());
        String arg = context.popString().toLowerCase();
        if (!subcommands.containsKey(arg.toLowerCase())) return Collections.emptyList();
        return subcommands.get(arg).suggest(context);
    }

    public MultiCommand<S> addSubcommand(String name, CommandHandler<S> executor) {
        subcommands.put(name.toLowerCase(), executor);
        return this;
    }

    @SuppressWarnings("unchecked")
    private void resortMessages() {
        pMessages.clear();
        messages.clear();
        for (CommandHandler<S> handler : subcommands.values()) {
            if (handler instanceof PrioritizedMessage) pMessages.add((PrioritizedMessage<S>) handler);
            else if (handler instanceof Message) messages.add((Message<S>) handler);
        }
        pMessages.sort(Comparator.comparingInt(PrioritizedMessage::getPriority));
    }

    @Override
    public void send(CommandContext<S> context) {
        for (PrioritizedMessage<S> msg : pMessages) msg.send(context);
        for (Message<S> msg : messages) msg.send(context);
    }

    @Override
    public int getPriority() {
        return linePriority;
    }
}
