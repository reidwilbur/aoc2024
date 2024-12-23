package com.wilb0t.aoc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class Day23 {

  public static int getHistorianLanGames(Map<String, Set<String>> edges) {
    var connected = new HashSet<Set<String>>();
    for (var node : edges.keySet()) {
      var nbors = edges.get(node);
      for (var nbor : nbors) {
        nbors.stream()
            .filter(Predicate.not(nbor::equals))
            .filter(third -> edges.get(nbor).contains(third))
            .forEach(third -> connected.add(Set.of(node, nbor, third)));
      }
    }
    return (int) connected.stream().filter(game -> game.stream().anyMatch(name -> name.startsWith("t"))).count();
  }

  public static Map<String, Set<String>> parse(List<String> input) {
    Map<String, Set<String>> result = new HashMap<>();
    for (String line : input) {
      var parts = line.split("-");
      if (result.containsKey(parts[0])) {
        result.get(parts[0]).add(parts[1]);
      } else {
        result.put(parts[0], new HashSet<>(List.of(parts[1])));
      }
      if (result.containsKey(parts[1])) {
        result.get(parts[1]).add(parts[0]);
      } else {
        result.put(parts[1], new HashSet<>(List.of(parts[0])));
      }
    }
    return result;
  }
}
