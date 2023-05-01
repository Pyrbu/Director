package lol.pyr.director.common.command;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.ParserType;

import java.util.HashMap;
import java.util.Map;

public abstract class CommandManager<S> {
    private final Map<Class<?>, ParserType<?, S>> parserMap = new HashMap<>();
    private final Message<S> defaultMessage;
    private Message<S> notEnoughArgumentsMessage;

    public CommandManager(Message<S> defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @SuppressWarnings("unchecked")
    protected <T> ParserType<T, S> getParser(Class<T> type) {
        return (ParserType<T, S>) parserMap.get(type);
    }

    public <T> void registerParser(Class<T> clazz, ParserType<T, S> parser) {
        parserMap.put(clazz, parser);
    }

    public Message<S> getDefaultMessage() {
        return defaultMessage;
    }

    public Message<S> getNotEnoughArgumentsMessage() {
        return notEnoughArgumentsMessage;
    }

    public void setNotEnoughArgumentsMessage(Message<S> notEnoughArgumentsMessage) {
        this.notEnoughArgumentsMessage = notEnoughArgumentsMessage;
    }

    public abstract void registerCommand(String name, CommandHandler<S> command);
}
