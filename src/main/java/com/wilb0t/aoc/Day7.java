package com.wilb0t.aoc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day7 {

  public static long getCalResult(List<Calibration> input) {
    return input.stream().filter(Day7::isValid).mapToLong(cal -> cal.result).sum();
  }

  public static long getCalResultConcat(List<Calibration> input) {
    return input.stream().filter(Day7::isValidConcat).mapToLong(cal -> cal.result).sum();
  }

  static boolean isValidConcat(Calibration calibration) {
    var perm = new Op[calibration.nums.size() - 1];
    Arrays.fill(perm, Op.values()[0]);
    var done = false;
    while (!done) {
      long acc = calibration.nums.getFirst();
      for (int idx = 1; idx < calibration.nums.size(); idx++) {
        var op = perm[idx - 1];
        switch (op) {
          case ADD -> acc *= calibration.nums.get(idx);
          case MUL -> acc += calibration.nums.get(idx);
          case CCT -> acc = concat(acc, calibration.nums.get(idx));
        }
      }
      if (acc == calibration.result) {
        return true;
      }
      done = !nextPerm(perm);
    }
    return false;
  }

  static long concat(long l, long r) {
    int pow = 1;
    long work = r;
    while (work >= 10) {
      work /= 10;
      pow += 1;
    }
    for (var idx = 0; idx < pow; idx++) {
      l *= 10;
    }
    return l + r;
  }

  enum Op {
    ADD,
    MUL,
    CCT
  };

  static boolean nextPerm(Op[] curr) {
    for (var idx = 0; idx < curr.length; idx++) {
      if (curr[idx].ordinal() < Op.values().length - 1) {
        curr[idx] = Op.values()[curr[idx].ordinal() + 1];
        return true;
      } else {
        curr[idx] = Op.values()[0];
      }
    }
    return false;
  }

  static boolean isValid(Calibration calibration) {
    var perms = 1 << (calibration.nums.size() - 1);
    for (var perm = 0; perm <= perms; perm++) {
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
