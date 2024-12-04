package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Day1 {
  public static int getDist(Input input) {
    var left = input.left.stream().sorted().toList();
    var right = input.right.stream().sorted().toList();

    int dist = 0;
    for (int i = 0; i < left.size(); i++) {
      dist += Math.abs(left.get(i) - right.get(i));
    }
    return dist;
  }

  public static int getSimilarity(Input input) {
    var rightFreq = input.right.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    return input.left.stream()
        .mapToInt(entry -> entry * rightFreq.getOrDefault(entry, 0L).intValue())
        .sum();
  }

  public record Input(List<Integer> left, List<Integer> right) {}

  public static Input parse(List<String> lines) {
    var left = new ArrayList<Integer>();
    var right = new ArrayList<Integer>();
    for (var line : lines) {
      var parts = line.split("\\s+");
      left.add(Integer.parseInt(parts[0]));
      right.add(Integer.parseInt(parts[1]));
    }
    return new Input(left, right);
  }
}
