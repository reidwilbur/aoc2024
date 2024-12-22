package com.wilb0t.aoc;

public class Day22 {

  public static long getSecretNumSum(int[] input) {
    long sum = 0;
    for (var num : input) {
      long next = num;
      for (var i = 0; i < 2000; i++) {
        next = getNextNum(next);
      }
      sum += next;
    }
    return sum;
  }

  static long getNextNum(long num) {
    var pval = 16777216 - 1;
    long next = num;
    next = ((next << 6) ^ next) & (pval);
    next = ((next >> 5) ^ next) & (pval);
    next = ((next << 11) ^ next) & (pval);
    return next;
  }
}
