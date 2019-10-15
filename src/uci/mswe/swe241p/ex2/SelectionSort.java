package uci.mswe.swe241p.ex2;

import java.util.List;

/**
 * SelectionSort
 */
public class SelectionSort extends Sort {
  @Override
  void sort(List<String> wordList) {
    final var len = wordList.size();
    if (len <= 1) {
      return;
    }

    for (int i = 0; i < len - 1; ++i) {
      // if (i % 10000 == 0) {
      // System.out.println("Iteration " + i + "/" + len);
      // }

      // assume the starting one is the minimun word
      int wordMinIndex = i;
      // then we look ahead to find smaller ones
      for (int j = i + 1; j < len; ++j) {
        var wordListAtJ = wordList.get(j);
        var wordMin = wordList.get(wordMinIndex);
        if (wordListAtJ.compareTo(wordMin) < 0) {
          wordMinIndex = j;
        }
      }
      // finally we have found the smallest and move it to a smaller index
      swap(wordList, i, wordMinIndex);
    }
  }
}