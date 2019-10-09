package uci.mswe.swe241p.ex2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

  public static void main(String[] args) {
    List<String> wordList = new ArrayList<String>();
    // loadBookTo(wordList);
    loadRandomTo(wordList, 10);
    System.out.println(wordList.size());

    final var runCount = 1;
    for (var i = 0; i < runCount; ++i) {
      new InsertionSort().run(wordList);
      System.out.println(wordList.toString());
      // new SelectionSort().run(wordList);
      // new HeapSort().run(wordList);
      // new MergeSort().run(wordList);
      // new QuickSort().run(wordList);
    }
  }

  public static void loadRandomTo(List<String> wordList, int count) {
    var rand = new Random();
    for (var i = 0; i < count; ++i) {
      wordList.add(Integer.toString(rand.nextInt(100)));
    }
  }

  public static void loadBookTo(List<String> wordList) {
    // read all words in the book and save them to the list
    try (Scanner scanner = new Scanner(new File("./data/in/pride-and-prejudice.txt"))) {
      while (scanner.hasNextLine()) {
        var line = scanner.nextLine();
        String[] words;

        words = line.split("[^\\w\\d_]");

        for (var word : words) {
          if (word != null && word.length() > 0) {
            wordList.add(word);
          }
        }
      }
      // scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }
  }
}