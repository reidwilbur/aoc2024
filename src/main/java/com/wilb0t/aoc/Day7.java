package com.wilb0t.aoc;

import java.util.List;
import java.util.stream.Stream;

public class Day7 {

  public static long getCalResult(List<Calibration> input) {
    return input.stream()
        .filter(Day7::isValid)
        .mapToLong(cal -> cal.result)
        .sum();
  }

  static boolean isValid(Calibration calibration) {
    var perms = 1 << (calibration.nums.size() - 1);
    for (var perm = 0; perm <= perms ; perm++) {
      long acc = calibration.nums.getFirst();
      for (var idx = 1; idx < calibration.nums.size(); idx++) {
        switch ((perm & (0x1 << (idx - 1))) != 0) {
          case true -> acc *= calibration.nums.get(idx);
          case false -> acc += calibration.nums.get(idx);
        }
      }
      if (acc == calibration.result) {
        return true;
      }
    }
    return false;
  }

  public record Calibration(long result, List<Integer> nums) {}

  public static Calibration parse(String input) {
    var parts = input.split(": ");
    var result = Long.parseLong(parts[0]);
    var nums = Stream.of(parts[1].split(" ")).map(Integer::parseInt).toList();
    return new Calibration(result, nums);
  }
}
