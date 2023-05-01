package lol.pyr.director.standalone.message;

import lol.pyr.director.common.command.CommandContext;
import lol.pyr.director.common.message.Message;

public class StandaloneStaticMessage implements Message<DirectorReceiver> {
    private final String message;

    public StandaloneStaticMessage(String message) {
        this.message = message;
    }

    @Override
    public void send(CommandContext<DirectorReceiver> context) {
        context.getSender().send(message);
    }
}
