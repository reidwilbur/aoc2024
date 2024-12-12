package com.wilb0t.aoc;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Day12 {

  public static long getFenceCost(char[][] garden) {
    var rows = garden.length;
    var cols = garden[0].length;

    var visited = new HashSet<Coord>();
    long cost = 0;
    for (var row = 0; row < rows; row++) {
      for (var col = 0; col < cols; col++) {
        var pos = new Coord(row, col);
        if (!visited.contains(pos)) {
          var region = getRegion(pos, garden);
          visited.addAll(region);
          cost += getRegionCost(region, garden);
        }
      }
    }
    return cost;
  }

  record Coord(int r, int c) {
    public List<Coord> nbors(int rows, int cols) {
      return Stream.of(new Coord(r - 1, c), new Coord(r + 1, c), new Coord(r, c - 1), new Coord(r, c + 1))
          .filter(c -> c.isValid(rows, cols))
          .toList();
    }

    public Stream<Coord> nbors() {
      return Stream.of(new Coord(r - 1, c), new Coord(r + 1, c), new Coord(r, c - 1), new Coord(r, c + 1));
    }

    public boolean isValid(int rows, int cols) {
      return (r >= 0) && (r < rows) && (c >= 0) && (c < cols);
    }
  }

  static long getRegionCost(Set<Coord> region, char[][] garden) {
    var rows = garden.length;
    var cols = garden[0].length;
    long perimeter = 0;
    for (var coord : region) {
      var edges = coord.nbors()
          .filter(n -> !n.isValid(rows, cols) || garden[n.r][n.c] != garden[coord.r][coord.c])
          .count();
      perimeter += edges;
    }
    return perimeter * region.size();
  }

  static Set<Coord> getRegion(Coord pos, char[][] garden) {
    var rows = garden.length;
    var cols = garden[0].length;
    char name = garden[pos.r][pos.c];

    var next = new ArrayDeque<Coord>();
    next.add(pos);
    var visited = new HashSet<Coord>();
    var region = new HashSet<Coord>();
    while (!next.isEmpty()) {
      var coord = next.remove();
      if (!visited.contains(coord)) {
        visited.add(coord);
        if (garden[coord.r][coord.c] == name) {
          region.add(coord);
          next.addAll(coord.nbors(rows, cols));
        }
      }
    }
    return region;
  }
}
