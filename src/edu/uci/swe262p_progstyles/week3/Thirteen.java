package edu.uci.swe262p_progstyles.week3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Junxian Chen on 2020-04-16.
 *
 * @see <a href="https://github.com/crista/exercises-in-programming-style/tree/master/14-abstract-things">abstract-things</a>
 */
public class Thirteen {

    private interface ITextLinesManager {
        Stream<String> getLines();
    }

    private static class TextLinesManager implements ITextLinesManager {

        private Stream<String> lines;

        public TextLinesManager(String spath) {
            try {
                lines = Files.lines(Path.of(spath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Stream<String> getLines() {
            return lines;
        }
    }

    private interface IStopWordsManager {
        boolean isStopWord(String w);
    }

    private static class StopWordsManager implements IStopWordsManager {

        private final Set<String> stopWords = new HashSet<>();

        public StopWordsManager() {
            // load stop words
            final String PATH_STOP_WORDS = "data/swe262p/stop_words.txt";
            try {
                final byte[] bytes = Files.readAllBytes(Path.of(PATH_STOP_WORDS));
                final String[] words = new String(bytes).split(",");
                stopWords.addAll(Arrays.asList((words)));
            } catch (IOException e) {
                System.err.println("Error reading stop_words.txt");
                System.exit(1);
            }
        }

        @Override
        public boolean isStopWord(String w) {
            return stopWords.contains(w);
        }
    }

    private interface IFrequencyMapManager {
        HashMap<String, Integer> getFrequencyMap();

        void increment(String w);
    }

    private static class FrequencyMapManager implements IFrequencyMapManager {

        private final HashMap<String, Integer> frequencyMap = new HashMap<>();

        @Override
        public HashMap<String, Integer> getFrequencyMap() {
            return frequencyMap;
        }

        @Override
        public void increment(String w) {
            if (frequencyMap.containsKey(w)) {
                frequencyMap.put(w, frequencyMap.get(w) + 1);
            } else {
                frequencyMap.put(w, 1);
            }
        }
    }

    private static class MainController {

        private final TextLinesManager textLinesManager;
        private final StopWordsManager stopWordsManager;
        private final FrequencyMapManager frequencyMapManager;

        public MainController(String spath) {
            this.textLinesManager = new TextLinesManager(spath);
            this.stopWordsManager = new StopWordsManager();
            this.frequencyMapManager = new FrequencyMapManager();
        }

        public void run() {
            // start counting
            textLinesManager.getLines().forEach(line -> {
                try {
                    String[] words = line.split("[^a-zA-Z]+");
                    for (String word : words) {
                        String w = word.toLowerCase();
                        if (!stopWordsManager.isStopWord(w) && w.length() > 1) {
                            frequencyMapManager.increment(w);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // sort results
            final List<Map.Entry<String, Integer>> descendingList = new ArrayList<>(
                    frequencyMapManager.getFrequencyMap().entrySet());
            descendingList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            // print first 25 words
            final StringBuilder result = new StringBuilder();
            for (int i = 0; i < 25; ++i) {
                final Map.Entry<String, Integer> entry = descendingList.get(i);
                result.append(entry.getKey()).append("  -  ").append(entry.getValue()).append("\n");
            }
            System.out.println(result);
        }
    }

    private static void validateArguments(String[] args) {
        // process arguments
        if (args.length != 1) {
            System.err.println("Please provide exactly ONE argument. Current: " + args.length);
            System.exit(1);
        }

        final Path path = Path.of(args[0]);
        if (!path.toFile().exists()) {
            System.err.println(path + " does not exist.");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        validateArguments(args);
        new MainController(args[0]).run();
    }

}