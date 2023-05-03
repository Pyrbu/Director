package lol.pyr.director.common.command;

import lol.pyr.director.common.message.Message;
import lol.pyr.director.common.parse.CommonParserType;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public abstract class CommonCommandManager<Sender, Handler extends CommonCommandHandler<?>, Context> {
    private final Map<Class<?>, CommonParserType<?, Context>> parserMap = new HashMap<>();
    private final Message<Context> defaultMessage;
    private Message<Context> notEnoughArgumentsMessage;

    public CommonCommandManager(Message<Context> defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @SuppressWarnings("unchecked")
    protected <T> CommonParserType<T, Context> getParser(Class<T> type) {
        return (CommonParserType<T, Context>) parserMap.get(type);
    }

    public <T> void registerParser(Class<T> clazz, CommonParserType<T, Context> parser) {
        parserMap.put(clazz, parser);
    }

    public Message<Context> getDefaultMessage() {
        return defaultMessage;
    }

    public Message<Context> getNotEnoughArgumentsMessage() {
        return notEnoughArgumentsMessage;
    }

    public void setNotEnoughArgumentsMessage(Message<Context> notEnoughArgumentsMessage) {
        this.notEnoughArgumentsMessage = notEnoughArgumentsMessage;
    }

    public abstract void registerCommand(String name, Handler command);
}
