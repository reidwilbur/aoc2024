package com.wilb0t.aoc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 {

  public static long getStoneCount(int rounds, List<String> stones) {
    return getStoneCount(rounds, stones, new HashMap<>());
  }

  record MemoKey(String stone, int round) {}

  static long getStoneCount(int rounds, List<String> stones, Map<MemoKey, Long> memo) {
    if (rounds == 0) {
      return stones.size();
    }
    long size = 0;
    for (String stone : stones) {
      var memoKey = new MemoKey(stone, rounds);
      if (memo.containsKey(memoKey)) {
        size += memo.get(memoKey);
      } else {
        var nextStones = execRound(stone);
        var nextSize = getStoneCount(rounds - 1, nextStones, memo);
        memo.put(memoKey, nextSize);
        size += nextSize;
      }
    }
    return size;
  }

  static List<String> execRound(String stone) {
    if (stone.equals("0")) {
      return List.of("1");
    } else if ((stone.length() & 0x1) == 0) {
      var split = stone.length() / 2;
      var left = stone.substring(0, split);
      var right = stone.substring(split);
      right = (right.charAt(0) != '0') ? right : String.valueOf(Long.parseLong(right));
      return List.of(left, right);
    } else {
      return List.of(String.valueOf(Long.parseLong(stone) * 2024L));
    }
  }

  public static List<String> parse(List<String> input) {
    var parts = input.getFirst().split(" ");
    return Arrays.asList(parts);
  }
}
