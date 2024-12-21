package com.wilb0t.aoc;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

public class Day16 {

  public static long getLowestPath(char[][] maze) {
    var result = dijkstra(maze);
    return result.dist.entrySet().stream()
        .filter(e -> e.getKey().pos.equals(result.end))
        .min(Comparator.comparingInt(Entry::getValue))
        .get()
        .getValue();
  }

  record Node(Pos pos, Dir dir) {}

  record Result(Pos start, Pos end, Map<Node, Integer> dist, Map<Node, Set<Node>> prev) {}

  static Result dijkstra(char[][] maze) {
    var rows = maze.length;
    var cols = maze[0].length;
    var dist = new HashMap<Node, Integer>();
    var unvisited = new PriorityQueue<Node>(Comparator.comparingInt(dist::get));
    var prev = new HashMap<Node, Set<Node>>();
    Pos end = null;
    Pos start = null;
    for (var row = 0; row < rows; row++) {
      for (var col = 0; col < cols; col++) {
        if (maze[row][col] != '#') {
          var pos = new Pos(row, col);
          if (maze[row][col] == 'E') {
            end = pos;
          }
          if (maze[row][col] == 'S') {
            start = pos;
          }
          for (var dir : Dir.values()) {
            var node = new Node(pos, dir);
            dist.put(node, Integer.MAX_VALUE);
            prev.put(node, new HashSet<>());
          }
        }
      }
    }
    dist.put(new Node(start, Dir.E), 0);
    unvisited.addAll(dist.keySet());

    while (!unvisited.isEmpty() && dist.get(unvisited.peek()) != Integer.MAX_VALUE) {
      var cur = unvisited.poll();
      var nbors =
          List.of(
              new Node(new Pos(cur.pos.row - 1, cur.pos.col), Dir.N),
              new Node(new Pos(cur.pos.row + 1, cur.pos.col), Dir.S),
              new Node(new Pos(cur.pos.row, cur.pos.col + 1), Dir.E),
              new Node(new Pos(cur.pos.row, cur.pos.col - 1), Dir.W));
      for (var nbor : nbors) {
        if (dist.containsKey(nbor) && unvisited.contains(nbor)) {
          var turnCost =
              switch (Math.abs(cur.dir.ordinal() - nbor.dir.ordinal())) {
                case 0 -> 0;
                case 1, 3 -> 1000;
                case 2 -> 2000;
                default -> throw new IllegalStateException("Unexpected value: " + cur.dir);
              };
          var alt = 1 + turnCost + dist.get(cur);
          if (dist.get(nbor) > alt) {
            dist.put(nbor, alt);
            unvisited.remove(nbor);
            unvisited.add(nbor);
            prev.get(nbor).clear();
            prev.get(nbor).add(cur);
          } else if (dist.get(nbor) == alt) {
            prev.get(nbor).add(cur);
          }
        }
      }
    }
    return new Result(start, end, dist, prev);
  }

  public static long getBestSeatCount(char[][] maze) {
    var result = dijkstra(maze);
    var queue = new ArrayDeque<Node>();
    var end =
        result.prev.keySet().stream()
            .filter(n -> n.pos.equals(result.end))
            .min(Comparator.comparingInt(result.dist::get))
            .get();
    queue.add(end);
    var tiles = new HashSet<Pos>();
    while (!queue.isEmpty()) {
      var node = queue.removeFirst();
      tiles.add(node.pos);
      var prev = result.prev.get(node);
      queue.addAll(prev);
    }
    return tiles.size();
  }

  public record Pos(int row, int col) {}

  public enum Dir {
    N,
    E,
    S,
    W
  }
}
