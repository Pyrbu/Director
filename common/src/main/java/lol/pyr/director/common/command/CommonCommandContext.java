package lol.pyr.director.common.command;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.CommonParserType;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class CommonCommandContext<Manager extends CommonCommandManager<Sender, Handler, Context>, Context, Sender, Handler extends CommonCommandHandler<?>> {
    private final Manager manager;
    private final Sender sender;
    private final String label;
    private final Deque<String> args;

    private String usage;
    private Message<Context> lastMsg;

    public CommonCommandContext(Manager manager, Sender sender, String label, Deque<String> args) {
        this.manager = manager;
        this.sender = sender;
        this.label = label;
        this.args = args;
    }

    public Sender getSender() {
        return sender;
    }

    public String getLabel() {
        return label;
    }

    public Manager getManager() {
        return manager;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    protected void setLastMessage(Message<Context> msg) {
        this.lastMsg = msg;
    }

    public Message<Context> getLastMessage() {
        return lastMsg;
    }

    public <T> T parse(Class<T> type) throws CommandExecutionException {
        try {
            CommonParserType<T, Context> parser = manager.getParser(type);
            setLastMessage(parser);
            return parser.parse(args);
        } catch (NoSuchElementException exception) {
            throw new CommandExecutionException();
        }
    }

    public <T> T suggestionParse(int offset, Class<T> type) throws CommandExecutionException {
        try {
            ArrayList<String> list = new ArrayList<>(args);
            ArrayDeque<String> realArgs = new ArrayDeque<>();
            for (int i = offset; i < args.size(); i++) realArgs.addFirst(list.get(i));
            return manager.getParser(type).parse(realArgs);
        } catch (NoSuchElementException exception) {
            throw new CommandExecutionException();
        }
    }

    public void ensureArgsNotEmpty() throws CommandExecutionException {
        setLastMessage(manager.getNotEnoughArgumentsMessage());
        if (argSize() == 0) throw new CommandExecutionException();
    }

    public String dumpAllArgs() {
        return String.join(" ", args);
    }

    public String popString() throws CommandExecutionException {
        setLastMessage(manager.getNotEnoughArgumentsMessage());
        if (argSize() == 0) throw new CommandExecutionException();
        return args.removeFirst();
    }

    public int argSize() {
        return args.size();
    }

    public boolean matchSuggestion(String... args) {
        if (args.length + 1 != this.args.size()) return false;
        if (args.length == 0) return true;
        Deque<String> clone = new ArrayDeque<>(this.args);
        for (String s : args) {
            String popped = clone.pop();
            if (!s.equals("*") && !s.equalsIgnoreCase(popped)) return false;
        }
        return true;
    }

    public List<String> suggestLiteral(String... args) throws CommandExecutionException {
        return suggestStream(Stream.of(args));
    }

    public List<String> suggestEnum(Enum<?>[] e) throws CommandExecutionException {
        return suggestStream(Arrays.stream(e).map(Enum::name));
    }

    public List<String> suggestCollection(Collection<String> args) throws CommandExecutionException {
        return suggestStream(args.stream());
    }

    public List<String> suggestStream(Stream<String> args) throws CommandExecutionException {
        ensureArgsNotEmpty();
        final String input = this.args.removeLast().toLowerCase();
        return args.filter(s -> s.toLowerCase().startsWith(input)).collect(Collectors.toList());
    }

    public void halt(Message<Context> message) throws CommandExecutionException {
        setLastMessage(message);
        throw new CommandExecutionException();
    }

    public void halt() throws CommandExecutionException {
        halt(null);
    }
}
