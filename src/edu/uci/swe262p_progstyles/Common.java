package edu.uci.swe262p_progstyles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Created by Junxian Chen on 2020-05-02.
 */
public class Common {

    public static final String PATH_STOP_WORDS = "data/swe262p/stop_words.txt";

    public static void main(String[] args) throws IOException {
//        loadStopWords();
        final Set<String> stopWords = new HashSet<>();
        Files.lines(Path.of("data/swe262p/stop_words.txt"))
                .forEach(line -> Collections.addAll(stopWords, line.split(",")));
        stopWords.addAll(Arrays.asList("abcdefghijklmnopqrstuvwxyz".split("")));

//        loadWords(args[0]);
        final List<String> words = new ArrayList<>();
        Files.lines(Path.of(args[0]))
                .forEach(line -> Arrays.stream(line.split("[^a-zA-Z]+"))
                        .map(String::toLowerCase)
                        .filter(word -> !stopWords.contains(word) && word.length() > 1)
                        .forEach(words::add));

//        count();
        final Map<String, Integer> map = new HashMap<>();
        words.forEach(word -> {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        });

//        sortAndPrint();
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((a, b) -> b - a))
                .limit(25)
                .forEach(entry -> System.out.println(entry.getKey() + "  -  " + entry.getValue()));
    }
}
