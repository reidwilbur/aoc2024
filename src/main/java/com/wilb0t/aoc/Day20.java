package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day20 {

  public static Map<Integer, Long> getCheats(char[][] map) {
    var path = getPath(map);
    var cheats = new HashMap<Cheat, Integer>();
    for (Pos pos : path.time.keySet()) {
      for (Pos cheat : pos.cheats(map, path.time.keySet())) {
        var first = (path.time.get(pos) > path.time.get(cheat)) ? cheat : pos;
        var last = (path.time.get(pos) > path.time.get(cheat)) ? pos : cheat;
        var cheatTime = path.time.get(last) - path.time.get(first) - 2;
        cheats.put(new Cheat(first, last), cheatTime);
      }
    }
    return cheats.entrySet().stream()
        .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.counting()));
  }

  public static Map<Integer, Long> getLongCheats(char[][] map) {
    var path = getPath(map);
    var cheats = new HashMap<Cheat, Integer>();
    for (Pos pos : path.time.keySet()) {
      for (Pos cheat : pos.cheats20ps(path.time)) {
        var first = (path.time.get(pos) > path.time.get(cheat)) ? cheat : pos;
        var last = (path.time.get(pos) > path.time.get(cheat)) ? pos : cheat;
        var dist = Math.abs(last.row - first.row) + Math.abs(last.col - first.col);
        var cheatTime = path.time.get(last) - path.time.get(first) - dist;
        if (cheatTime > 0) {
          cheats.put(new Cheat(first, last), cheatTime);
        }
      }
    }
    return cheats.entrySet().stream()
        .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.counting()));
  }

  record Cheat(Pos start, Pos end) {}

  static Path getPath(char[][] map) {
    var rows = map.length;
    var cols = map[0].length;
    var times = new HashMap<Pos, Integer>();
    var start = new Pos(0, 0);
    var end = new Pos(0, 0);
    for (var row = 0; row < rows; row++) {
      for (var col = 0; col < cols; col++) {
        if (map[row][col] != '#') {
          var pos = new Pos(row, col);
          times.put(new Pos(row, col), 0);
          if (map[row][col] == 'S') {
            start = pos;
          }
          if (map[row][col] == 'E') {
            end = pos;
          }
        }
      }
    }
    var time = 0;
    var cur = start;
    times.put(start, 0);
    while (!cur.equals(end)) {
      var next =
          cur.nbors()
              .filter(nbor -> times.containsKey(nbor) && times.get(nbor) == 0)
              .findFirst()
              .get();
      time += 1;
      times.put(next, time);
      cur = next;
    }
    return new Path(start, end, times);
  }

  public record Path(Pos start, Pos end, Map<Pos, Integer> time) {}

  public record Pos(int row, int col) {
    public Stream<Pos> nbors() {
      return Stream.of(
          new Pos(row - 1, col),
          new Pos(row + 1, col),
          new Pos(row, col - 1),
          new Pos(row, col + 1));
    }

    public List<Pos> cheats(char[][] map, Set<Pos> path) {
      var cheats = new ArrayList<Pos>();
      var cheatEnd = new Pos(row + 2, col);
      if (map[row + 1][col] == '#' && path.contains(cheatEnd)) {
        cheats.add(cheatEnd);
      }
      cheatEnd = new Pos(row - 2, col);
      if (map[row - 1][col] == '#' && path.contains(cheatEnd)) {
        cheats.add(cheatEnd);
      }
      cheatEnd = new Pos(row, col + 2);
      if (map[row][col + 1] == '#' && path.contains(cheatEnd)) {
        cheats.add(cheatEnd);
      }
      cheatEnd = new Pos(row, col - 2);
      if (map[row][col - 1] == '#' && path.contains(cheatEnd)) {
        cheats.add(cheatEnd);
      }
      return cheats;
    }

    public List<Pos> cheats20ps(Map<Pos, Integer> path) {
      var cheats = new ArrayList<Pos>();
      var curTime = path.get(this);
      for (var next : path.keySet()) {
        var nextTime = path.get(next);
        if (nextTime > curTime) {
          var mapDist = Math.abs(next.row - row) + Math.abs(next.col - col);
          if (mapDist >= 2 && mapDist <= 20) {
            cheats.add(next);
          }
        }
      }
      return cheats;
    }
  }
}
