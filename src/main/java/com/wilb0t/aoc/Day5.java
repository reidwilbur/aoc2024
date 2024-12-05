package com.wilb0t.aoc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5 {

  public static int getValidMiddleSum(Input input) {
    return input.updates.stream()
        .filter(update -> isValidUpdate(input.ordering, update))
        .mapToInt(Day5::getMiddle)
        .sum();
  }

  public static int getFixedMiddleSum(Input input) {
    return input.updates.stream()
        .filter(update -> !isValidUpdate(input.ordering, update))
        .map(update -> fixUpdate(input.ordering, update))
        .mapToInt(Day5::getMiddle)
        .sum();
  }

  static List<Integer> fixUpdate(Map<Integer, Set<Integer>> ordering, List<Integer> update) {
    var fixed = new ArrayList<Integer>();
    fixed.add(update.getFirst());
    for (var page: update.subList(1, update.size())) {
      var lidx = getLeftIdx(ordering, fixed, page);
      var nfix = new ArrayList<Integer>();
      if (lidx == -1) {
        nfix.add(page);
        nfix.addAll(fixed);
      } else {
        nfix.addAll(fixed.subList(0, lidx + 1));
        nfix.add(page);
        nfix.addAll(fixed.subList(lidx + 1, fixed.size()));
      }
      fixed = nfix;
    }
    return fixed;
  }

  static int getLeftIdx(Map<Integer, Set<Integer>> ordering, List<Integer> update, int page) {
    var lidx = -1;
    for (var idx = 0; idx < update.size(); idx++) {
      if (ordering.getOrDefault(update.get(idx), Set.of()).contains(page)) {
        lidx = idx;
      }
    }
    return lidx;
  }

  static int getMiddle(List<Integer> update) {
    return update.get(update.size() / 2);
  }

  static boolean isValidUpdate(Map<Integer, Set<Integer>> ordering, List<Integer> update) {
    var seen = new ArrayDeque<Integer>();
    for (var page: update) {
      var pageOrdering = ordering.getOrDefault(page, Set.of());
      if (pageOrdering.stream().anyMatch(seen::contains)) {
        return false;
      }
      seen.add(page);
    }
    return true;
  }

  public record Input(Map<Integer, Set<Integer>> ordering, List<List<Integer>> updates) {}

  public static Input parse(List<String> input) {
    var split = input.indexOf("");
    Map<Integer, Set<Integer>> ordering = input.subList(0, split).stream()
        .map(line -> {
          var parts = line.split("\\|");
          return Map.entry(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        })
        .collect(
            Collectors.toMap(
                Map.Entry::getKey,
                entry -> Set.of(entry.getValue()),
                (l, r) -> Stream.concat(l.stream(), r.stream()).collect(Collectors.toSet())));
    var updates = input.subList(split + 1, input.size()).stream()
        .map(line -> Stream.of(line.split(",")).map(Integer::parseInt).toList())
        .toList();
    return new Input(ordering, updates);
  }
}
