package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day2 {

  public static int getSafeCount(List<List<Integer>> reports) {
    var count = 0;
    for (var report : reports) {
      count += (isSafe(report)) ? 1 : 0;
    }
    return count;
  }

  public static int getSafeCountWithDampener(List<List<Integer>> reports) {
    var count = 0;
    for (var report : reports) {
      if (isSafe(report) || hasSafeSubReport(report)) {
        count += 1;
      }
    }
    return count;
  }

  static boolean hasSafeSubReport(List<Integer> report) {
    for (var i = 0; i < report.size(); i++) {
      var subReport = new ArrayList<>(report);
      subReport.remove(i);
      if (isSafe(subReport)) {
        return true;
      }
    }
    return false;
  }

  static boolean isSafe(List<Integer> report) {
    var lastDir = 0;
    for (var left = 0; left < report.size() - 1; left += 1) {
      var cur = report.get(left);
      var nxt = report.get(left + 1);
      var dir = (cur > nxt) ? -1 : (cur.equals(nxt)) ? 0 : 1;
      var diff = Math.abs(cur - nxt);
      if ((diff == 0 || diff > 3) || ((left > 0) && dir != lastDir)) {
        return false;
      }
      lastDir = dir;
    }
    return true;
  }

  public static List<List<Integer>> parse(List<String> input) {
    return input.stream()
        .map(row -> Stream.of(row.split("\\s+")).map(Integer::parseInt).toList())
        .toList();
  }
}
