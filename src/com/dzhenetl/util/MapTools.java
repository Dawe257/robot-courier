package com.dzhenetl.util;

import java.util.Map;
import java.util.Optional;

public class MapTools {

    public static void updateMap(Map<Integer, Integer> map, int n) {
        if (map.containsKey(n)) {
            map.put(n, map.get(n) + 1);
        } else {
            map.put(n, 1);
        }
    }

    public static void printMap(Map<Integer, Integer> map) {
        Optional<Map.Entry<Integer, Integer>> max = map.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        System.out.printf("Самое частое количество повторений %d (встретилось %d раз)\n",
                max.get().getKey(), max.get().getValue());
        map.remove(max.get().getKey());

        System.out.println("Другие размеры:");
        map.forEach((k, v) -> {
            System.out.printf("- %d (%d раз)\n", k, v);
        });
    }
}
