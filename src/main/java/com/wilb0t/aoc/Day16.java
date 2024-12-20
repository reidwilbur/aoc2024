package com.wilb0t.aoc;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Stream;

public class Day16 {

  public static long getLowestPath(char[][] maze) {
    var rows = maze.length;
    var cols = maze[0].length;
    var dist = new HashMap<Node, Integer>();
    var unvisited = new PriorityQueue<Node>(Comparator.comparing(dist::get));
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
            unvisited.remove(nbor);
            dist.put(nbor, alt);
            unvisited.add(nbor);
          }
        }
      }
    }
    var eend = end;
    return dist.entrySet().stream()
        .filter(e -> e.getKey().pos.equals(eend))
        .min(Comparator.comparingInt(Entry::getValue))
        .get()
        .getValue();
  }

  record Node(Pos pos, Dir dir) {}

  public static long getBestSeatCount(char[][] maze) {
    return 0;
  }

  static void display(char[][] maze, Set<Pos> seats) {
    for (var row = 0; row < maze.length; row++) {
      var sb = new StringBuilder();
      for (var col = 0; col < maze[0].length; col++) {
        if (seats.contains(new Pos(row, col))) {
          sb.append('O');
        } else {
          sb.append(maze[row][col]);
        }
      }
      System.out.println(sb);
    }
    System.out.println();
  }

  static Pos getPos(char[][] maze, char label) {
    for (var row = 0; row < maze.length; row++) {
      for (var col = 0; col < maze[0].length; col++) {
        if (maze[row][col] == label) {
          return new Pos(row, col);
        }
      }
    }
    throw new RuntimeException("No pos found for " + label);
  }

  public record State(Pos pos, Dir dir, int score) {
    public List<State> nbors(char[][] maze) {
      var cw = Dir.values()[(this.dir.ordinal() + 1) % Dir.values().length];
      var ccw = Dir.values()[(this.dir.ordinal() + 3) % Dir.values().length];
      var rev = Dir.values()[(this.dir.ordinal() + 2) % Dir.values().length];
      return Stream.of(
              new State(this.pos.next(this.dir), this.dir, this.score + 1),
              new State(this.pos.next(cw), cw, this.score + 1 + 1000),
              new State(this.pos.next(ccw), ccw, this.score + 1 + 1000),
              new State(this.pos.next(rev), rev, this.score + 1 + 2000))
          .filter(s -> maze[s.pos.row][s.pos.col] != '#')
          .toList();
    }
  }

  public record Pos(int row, int col) {
    public Pos next(Dir dir) {
      return switch (dir) {
        case N -> new Pos(row - 1, col);
        case E -> new Pos(row, col + 1);
        case S -> new Pos(row + 1, col);
        case W -> new Pos(row, col - 1);
      };
    }
  }

  public enum Dir {
    N,
    E,
    S,
    W
  }
}
