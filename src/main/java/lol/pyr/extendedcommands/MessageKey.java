package lol.pyr.extendedcommands;

import java.util.function.Function;

/**
 * An enum containing keys for all of the possible error messages
 * sent by methods in {@link CommandContext}, use {@link CommandManager#setMessageResolver(MessageKey, Function)} to
 * customise the messages represented by these keys
 */
public enum MessageKey {
    SENDER_REQUIRED_PLAYER, NOT_ENOUGH_ARGS
}
