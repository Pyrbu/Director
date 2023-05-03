package lol.pyr.director.common.message;

public interface Message<Context> {
    void send(Context context);
}
