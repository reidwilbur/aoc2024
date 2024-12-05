package com.wilb0t.aoc;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5 {

  public static int getValidMiddleSum(Input input) {
    return input.updates.stream()
        .filter(update -> isValidUpdate(input.ordering, update))
        .mapToInt(Day5::getMiddle)
        .sum();
  }

  static int getMiddle(List<Integer> update) {
    return update.get(update.size() / 2);
  }

  static boolean isValidUpdate(Map<Integer, List<Integer>> ordering, List<Integer> update) {
    var seen = new ArrayDeque<Integer>();
    for (var page: update) {
      var pageOrdering = ordering.getOrDefault(page, List.of());
      if (pageOrdering.stream().anyMatch(seen::contains)) {
        return false;
      }
      seen.add(page);
    }
    return true;
  }

  public record Input(Map<Integer, List<Integer>> ordering, List<List<Integer>> updates) {}

  public static Input parse(List<String> input) {
    var split = input.indexOf("");
    Map<Integer, List<Integer>> ordering = input.subList(0, split).stream()
        .map(line -> {
          var parts = line.split("\\|");
          return Map.entry(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        })
        .collect(
            Collectors.toMap(
                Map.Entry::getKey,
                entry -> List.of(entry.getValue()),
                (l, r) -> Stream.concat(r.stream(), l.stream()).toList()));
    var updates = input.subList(split + 1, input.size()).stream()
        .map(line -> Stream.of(line.split(",")).map(Integer::parseInt).toList())
        .toList();
    return new Input(ordering, updates);
  }
}
