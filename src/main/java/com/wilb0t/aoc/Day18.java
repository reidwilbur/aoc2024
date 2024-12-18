package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Day18 {

  public static int getMinSteps(List<Pos> input, int rows, int cols) {
    var corrupted = new HashSet<>(input);
    var dist = new HashMap<Pos, Integer>();
    var unvisited = new PriorityQueue<Pos>(Comparator.comparingInt(dist::get));
    for (var row = 0; row < rows; row++) {
      for (var col = 0; col < cols; col++) {
        var pos = new Pos(row, col);
        if (!corrupted.contains(pos)) {
          dist.put(pos, Integer.MAX_VALUE);
          unvisited.add(pos);
        }
      }
    }
    dist.put(new Pos(0, 0), 0);
    while (!unvisited.isEmpty() && dist.get(unvisited.peek()) != Integer.MAX_VALUE) {
      var cur = unvisited.poll();
      if (cur.row == rows - 1 && cur.col == cols - 1) {
        return dist.get(cur);
      } else {
        cur.nbors()
            .filter(unvisited::contains)
            .forEach(
                nbor -> {
                  if (dist.get(cur) + 1 < dist.get(nbor)) {
                    unvisited.remove(nbor);
                    dist.put(nbor, dist.get(cur) + 1);
                    unvisited.add(nbor);
                  }
                });
      }
    }

    throw new IllegalStateException("no path to end");
  }

  public record Pos(int row, int col) {
    public Stream<Pos> nbors() {
      return Stream.of(
          new Pos(row - 1, col),
          new Pos(row + 1, col),
          new Pos(row, col - 1),
          new Pos(row, col + 1));
    }
  }

  public static List<Pos> parse(List<String> input) {
    var posList = new ArrayList<Pos>();
    for (String line : input) {
      var parts = line.split(",");
      posList.add(new Pos(Integer.parseInt(parts[1]), Integer.parseInt(parts[0])));
    }
    return posList;
  }
}
