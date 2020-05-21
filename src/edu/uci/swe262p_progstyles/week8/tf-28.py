#!/usr/bin/env python
# https://github.com/crista/exercises-in-programming-style/blob/master/28-lazy-rivers/tf-28.py

import operator
import string
import sys


def characters(filename):
    for line in open(filename):
        for c in line:
            yield c


def all_words(filename):
    is_start_char = True
    word = ""
    for char in characters(filename):
        if is_start_char:
            word = ""
            if char.isalnum():
                # We found the start of a word
                word = char.lower()
                is_start_char = False
            else:
                pass
        else:
            if char.isalnum():
                word += char.lower()
            else:
                # We found end of word, emit it
                is_start_char = True
                yield word


def non_stop_words(filename):
    stopwords = set(open('../stop_words.txt').read().split(',') + list(string.ascii_lowercase))
    for word in all_words(filename):
        if word not in stopwords:
            yield word


def count_and_sort(filename):
    freqs, i = {}, 1
    for word in non_stop_words(filename):
        freqs[word] = 1 if word not in freqs else freqs[word] + 1
        # if i % 5000 == 0:
        #     yield sorted(freqs.items(), key=operator.itemgetter(1), reverse=True)
        # i = i + 1
    yield sorted(freqs.items(), key=operator.itemgetter(1), reverse=True)


#
# The main function
#
for word_freqs in count_and_sort(sys.argv[1]):
    print("-----------------------------")
    for (w, c) in word_freqs[0:25]:
        print(w, '-', c)
