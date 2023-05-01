package lol.pyr.director.common.message;

public interface PrioritizedMessage<S> extends Message<S> {
    int getPriority();
}
