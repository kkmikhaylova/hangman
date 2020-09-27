package util;

import java.util.List;
import java.util.Random;

public class ListUtils {

    public static String getRandomItem(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

}
