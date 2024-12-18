package com.wilb0t.aoc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class Day18 {

  public static int getMinSteps(List<Pos> input, int rows, int cols) {
    var corrupted = new HashSet<>(input);
    var dist = new HashMap<Pos, Integer>();
    var next = new ArrayDeque<Pos>();
    for (var row = 0; row < rows; row++) {
      for (var col = 0; col < cols; col++) {
        var pos = new Pos(row, col);
        if (!corrupted.contains(pos)) {
          dist.put(pos, Integer.MAX_VALUE);
        }
      }
    }
    var start = new Pos(0, 0);
    dist.put(start, 0);
    next.add(start);
    while (!next.isEmpty()) {
      var cur = next.poll();
      if (cur.row == rows - 1 && cur.col == cols - 1) {
        return dist.get(cur);
      } else {
        cur.nbors()
            .filter(dist::containsKey)
            .forEach(
                nbor -> {
                  if (dist.get(nbor) == Integer.MAX_VALUE) {
                    dist.put(nbor, dist.get(cur) + 1);
                    next.add(nbor);
                  }
                });
      }
    }

    return -1;
  }

  public static Pos getBlocker(int startIdx, List<Pos> input, int rows, int cols) {
    var start = startIdx;
    var end = input.size() - 1;
    while (start != end) {
      var halfIdx = (end - start + 1) / 2 + start;
      var minSteps = getMinSteps(input.subList(0, halfIdx + 1), rows, cols);
      if (minSteps == -1) {
        end = halfIdx - 1;
      } else {
        start = halfIdx;
      }
    }
    return input.get(end + 1);
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
