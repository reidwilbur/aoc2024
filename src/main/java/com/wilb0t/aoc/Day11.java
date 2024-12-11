package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11 {

  public static int getStoneCount(int rounds, List<String> stones) {
    var round = 0;
    while (round < rounds) {
      var nxtStones = new ArrayList<String>(stones.size());
      for (String stone : stones) {
        if (stone.equals("0")) {
          nxtStones.add("1");
        } else if ((stone.length() & 0x1) == 0) {
          var split = stone.length() / 2;
          var left = stone.substring(0, split);
          var right = stone.substring(split);
          right = (right.charAt(0) != '0') ? right : String.valueOf(Long.parseLong(right));
          nxtStones.add(left);
          nxtStones.add(right);
        } else {
          nxtStones.add(String.valueOf(Long.parseLong(stone) * 2024L));
        }
      }
      stones = nxtStones;
      round += 1;
    }
    return stones.size();
  }

  public static List<String> parse(List<String> input) {
    var parts = input.getFirst().split(" ");
    return Arrays.asList(parts);
  }

}
