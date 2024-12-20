package com.wilb0t.aoc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day19 {

  public static int getValidDesignCount(Input input) {
    var cache = new HashMap<String, Boolean>();
    return (int)
        input.designs.stream()
            .filter(design -> isValidDesign(input.patterns, design, cache))
            .count();
  }

  public static long getValidDesignCombos(Input input) {
    var cache = new HashMap<String, Long>();
    return input.designs.stream()
        .mapToLong(d -> getValidDesignCombos(input.patterns, d, cache))
        .sum();
  }

  static boolean isValidDesign(List<String> pats, String design, Map<String, Boolean> cache) {
    if (cache.containsKey(design)) {
      return cache.get(design);
    }

    if (design.isEmpty()) {
      return true;
    }

    for (var pat : pats) {
      if (design.startsWith(pat)) {
        var isValid = isValidDesign(pats, design.substring(pat.length()), cache);
        cache.put(design, isValid);
        if (isValid) {
          return true;
        }
      }
    }
    return false;
  }

  static long getValidDesignCombos(List<String> pats, String design, Map<String, Long> cache) {
    if (cache.containsKey(design)) {
      return cache.get(design);
    }

    if (design.isEmpty()) {
      return 1;
    }

    long combos = 0;
    for (var pat : pats) {
      if (design.startsWith(pat)) {
        combos += getValidDesignCombos(pats, design.substring(pat.length()), cache);
      }
    }
    cache.put(design, combos);
    return combos;
  }

  public record Input(List<String> patterns, List<String> designs) {
    public static Input parse(List<String> input) {
      var pats = Arrays.asList(input.getFirst().split(", "));
      return new Input(pats, input.subList(2, input.size()));
    }
  }
}
