package com.wilb0t.aoc;

import java.util.List;
import java.util.regex.Pattern;

public class Day3 {

  public static long getMulTotal(List<String> input) {
    long total = 0;
    var pattern = Pattern.compile("mul\\(\\d+?,\\d+?\\)");
    for (var line : input) {
      var match = pattern.matcher(line);
      while (match.find()) {
        total += execMul(match.group(0));
      }
    }
    return total;
  }

  public static long getMulTotalWithEnables(List<String> input) {
    long total = 0;
    var pattern = Pattern.compile("mul\\(\\d+?,\\d+?\\)|do\\(\\)|don't\\(\\)");
    var enabled = true;
    for (var line : input) {
      var match = pattern.matcher(line);
      while (match.find()) {
        var instr = match.group(0);
        if (instr.equals("do()")) {
          enabled = true;
        }
        if (instr.equals("don't()")) {
          enabled = false;
        }
        if (instr.startsWith("mul") && enabled) {
          total += execMul(instr);
        }
      }
    }
    return total;
  }

  static long execMul(String input) {
    var parts = input.substring(4, input.length() - 1).split(",");
    return Long.parseLong(parts[0]) * Long.parseLong(parts[1]);
  }
}
