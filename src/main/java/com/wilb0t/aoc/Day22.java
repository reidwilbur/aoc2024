package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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

  public static long getMaxBananas(int[] input) {
    var monkeys = new ArrayList<Monkey>();
    var seqs = new HashSet<List<Integer>>();
    for (var num : input) {
      var monkey = Monkey.of(num, 2000);
      monkeys.add(monkey);
      seqs.addAll(monkey.seqVals.keySet());
    }

    var maxBananas = 0;
    var maxSeq = List.<Integer>of();
    for (var seq : seqs) {
      var bananas = 0;
      for (var monkey : monkeys) {
        bananas += monkey.seqVals.getOrDefault(seq, 0);
      }
      if (bananas > maxBananas) {
        maxBananas = bananas;
        maxSeq = seq;
      }
    }
    return maxBananas;
  }

  public record Monkey(Map<List<Integer>, Integer> seqVals) {
    public static Monkey of(int snum, int numDiffs) {
      var diffs = new ArrayList<Integer>();
      var nums = new ArrayList<Integer>();
      long last = snum;
      for (var i = 0; i < numDiffs; i++) {
        var next = getNextNum(last);
        var diff = (next % 10) - (last % 10);
        diffs.add((int) diff);
        nums.add((int) (next % 10));
        last = next;
      }

      var seqVals = new HashMap<List<Integer>, Integer>(numDiffs - 4);
      for (var i = 0; i < diffs.size() - 4; i++) {
        var seq = List.of(diffs.get(i), diffs.get(i + 1), diffs.get(i + 2), diffs.get(i + 3));
        if (!seqVals.containsKey(seq)) {
          seqVals.put(seq, nums.get(i + 3));
        }
      }
      return new Monkey(seqVals);
    }
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
