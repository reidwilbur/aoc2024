package com.wilb0t.aoc;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Day16 {

  public static long getLowestPath(char[][] maze) {
    var start = getPos(maze, 'S');
    var next = new PriorityQueue<>(Comparator.comparing(State::score));
    next.add(new State(start, Dir.E, 0));
    var visited = new HashSet<Pos>();
    var shortestPath = Long.MAX_VALUE;

    while (!next.isEmpty()) {
      var cur = next.remove();
      if (maze[cur.pos.row][cur.pos.col] == 'E') {
        shortestPath = Math.min(shortestPath, cur.score);
      } else if (!visited.contains(cur.pos)) {
        visited.add(cur.pos);
        next.addAll(cur.nbors(maze));
      }
    }
    return shortestPath;
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

  public enum Dir { N, E, S, W }
}
