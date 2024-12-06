package com.wilb0t.aoc;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Day6 {

  public static int getPositionCount(Input input) {
    var curPos = input.guard.pos;
    var curDir = input.guard.dir;
    var visited = new HashSet<>();

    while (inMap(curPos, input.rows, input.cols)) {
      visited.add(curPos);
      var nextPos = curPos.move(curDir);
      if (input.obstacles.contains(nextPos)) {
        curDir = curDir.turn();
      } else {
        curPos = nextPos;
      }
    }

    return visited.size();
  }

  static boolean inMap(Coord coord, int rows, int cols) {
    return (coord.r >= 0) && (coord.c >= 0) && (coord.r < rows) && (coord.c < cols);
  }

  public record Coord(int r, int c) {
    public Coord move(Dir dir) {
      return switch (dir) {
        case UP -> new Coord(r - 1, c);
        case RT -> new Coord(r, c + 1);
        case DN  -> new Coord(r + 1, c);
        case LT  -> new Coord(r, c - 1);
      };
    }
  }

  public enum Dir {
    UP, RT, DN, LT;

    public Dir turn() {
      return Dir.values()[((this.ordinal() + 1) % Dir.values().length)];
    }
  }

  public record Guard(Coord pos, Dir dir) {}

  public record Input(int rows, int cols, Set<Coord> obstacles, Guard guard) {}

  public static Input parse(List<String> input) {
    var rows = input.size();
    var cols = input.getFirst().length();

    var obstacles = new HashSet<Coord>();
    Optional<Guard> guard = Optional.empty();

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        var mapval = input.get(r).charAt(c);
        switch (mapval) {
          case '#' -> obstacles.add(new Coord(r, c));
          case '^' -> guard = Optional.of(new Guard(new Coord(r, c), Dir.UP));
        }
      }
    }

    return new Input(rows, cols, obstacles, guard.get());
  }
}
