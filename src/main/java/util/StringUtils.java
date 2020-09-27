package util;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

    public static List<Character> toCharList(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
    }
}
