package lol.pyr.extendedcommands.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class CompletionUtil {

    public static List<String> filter(String input, Stream<String> stream) {
        final String finalInput = input.toLowerCase();
        return stream.filter(s -> s.toLowerCase().startsWith(finalInput)).collect(Collectors.toList());
    }

    public static List<String> players(String input) {
        return filter(input,
                Bukkit.getOnlinePlayers().stream()
                .map(HumanEntity::getName));
    }

    public static List<String> literal(String input, String... strings) {
        return filter(input, Stream.of(strings));
    }

    public static <T extends Enum<T>> List<String> enums(String input, T[] e) {
        return filter(input, Arrays.stream(e).map(Enum::name));
    }

}
