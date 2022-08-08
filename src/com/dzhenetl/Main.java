package com.dzhenetl;

import com.dzhenetl.util.MapTools;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    static final int THREAD_COUNTER = 1000;

    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            int count = (int) generateRoute("RLRFR", 100)
                    .chars()
                    .filter(ch -> ch == 'R')
                    .count();
            synchronized (sizeToFreq) {
                MapTools.updateMap(sizeToFreq, count);
            }
        };

        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_COUNTER; i++) {
            es.execute(task);
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.MINUTES);

        synchronized (sizeToFreq) {
            MapTools.printMap(sizeToFreq);
        }
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}
