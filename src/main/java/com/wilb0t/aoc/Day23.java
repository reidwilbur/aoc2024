package com.wilb0t.aoc;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day23 {

  public static int getHistorianLanGames(Map<String, Set<String>> edges) {
    var connected = new HashSet<Set<String>>();
    for (var first : edges.keySet()) {
      var nbors = edges.get(first);
      for (var second : nbors) {
        nbors.stream()
            .filter(Predicate.not(second::equals))
            .filter(third -> edges.get(second).contains(third))
            .forEach(third -> connected.add(Set.of(first, second, third)));
      }
    }
    return (int)
        connected.stream()
            .filter(game -> game.stream().anyMatch(node -> node.startsWith("t")))
            .count();
  }

  public static String getLanPassword(Map<String, Set<String>> edges) {
    var cliques = new HashSet<Set<String>>();
    getCliques(cliques, Set.of(), edges.keySet(), Set.of(), edges);
    var maxClique = cliques.stream().max(Comparator.comparingInt(Set::size)).get();
    return maxClique.stream().sorted().collect(Collectors.joining(","));
  }

  static void getCliques(
      Set<Set<String>> cliques,
      Set<String> r,
      Set<String> p,
      Set<String> x,
      Map<String, Set<String>> edges) {
    if (p.isEmpty() && x.isEmpty()) {
      cliques.add(r);
      return;
    }
    var workP = new HashSet<>(p);
    var workX = new HashSet<>(x);
    for (var node : p) {
      var nextR = new HashSet<>(r);
      nextR.add(node);
      var nextP = new HashSet<>(workP);
      nextP.retainAll(edges.get(node));
      var nextX = new HashSet<>(workX);
      nextX.retainAll(edges.get(node));
      getCliques(cliques, nextR, nextP, nextX, edges);
      workP.remove(node);
      workX.add(node);
    }
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
