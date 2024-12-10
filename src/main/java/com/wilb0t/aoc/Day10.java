package com.wilb0t.aoc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class Day10 {

  public static int getTrailheadScore(int[][] input) {
    var trailheads = getTrailheads(input);
    final var rows = input.length;
    final var cols = input[0].length;

    var scores = new ArrayList<Integer>(trailheads.size());
    for (var trailhead : trailheads) {
      var next = new ArrayDeque<Coord>();
      var visited = new HashSet<Coord>();
      next.add(trailhead);
      var score = 0;
      while (!next.isEmpty()) {
        var coord = next.remove();
        if (!visited.contains(coord)) {
          visited.add(coord);
          if (input[coord.r][coord.c] == 9) {
            score += 1;
          } else {
            coord
                .adjacent(rows, cols)
                .filter(nc -> input[nc.r][nc.c] - input[coord.r][coord.c] == 1)
                .forEach(next::add);
          }
        }
      }
      scores.add(score);
    }
    return scores.stream().mapToInt(Integer::intValue).sum();
  }

  public static int getTrailheadHikingScore(int[][] input) {
    var trailheads = getTrailheads(input);

    var scores = new ArrayList<Integer>(trailheads.size());
    for (var trailhead : trailheads) {
      scores.add(getHikingScore(input, trailhead));
    }
    return scores.stream().mapToInt(Integer::intValue).sum();
  }

  static int getHikingScore(int[][] input, Coord coord) {
    if (input[coord.r][coord.c] == 9) {
      return 1;
    }
    var next =
        coord
            .adjacent(input.length, input[0].length)
            .filter(nc -> input[nc.r][nc.c] - input[coord.r][coord.c] == 1)
            .toList();
    var score = 0;
    for (var n : next) {
      score += getHikingScore(input, n);
    }
    return score;
  }

  static List<Coord> getTrailheads(int[][] map) {
    var trailheads = new ArrayList<Coord>();
    for (var r = 0; r < map.length; r++) {
      for (var c = 0; c < map[0].length; c++) {
        if (map[r][c] == 0) {
          trailheads.add(new Coord(r, c));
        }
      }
    }
    return trailheads;
  }

  record Coord(int r, int c) {
    boolean isValid(int rows, int cols) {
      return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    Stream<Coord> adjacent(int rows, int cols) {
      return Stream.of(
              new Coord(r - 1, c), new Coord(r + 1, c), new Coord(r, c - 1), new Coord(r, c + 1))
          .filter(c -> c.isValid(rows, cols));
    }
  }
}
