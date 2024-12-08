package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Day8 {

  public static int getAntiNodeCount(Input input) {
    var coords = new HashSet<Coord>();
    for (var entry : input.antennas.entrySet()) {
      var freqCoords = entry.getValue();
      for (var fidx = 0; fidx < freqCoords.size() - 1; fidx++) {
        var first = freqCoords.get(fidx);
        for (var sidx = fidx + 1; sidx < freqCoords.size(); sidx++) {
          var second = freqCoords.get(sidx);
          var rdif = second.r - first.r;
          var cdif = second.c - first.c;
          var fanode = new Coord(first.r - rdif, first.c - cdif);
          var sanode = new Coord(second.r + rdif, second.c + cdif);
          coords.add(fanode);
          coords.add(sanode);
        }
      }
    }

    return (int) coords.stream()
        .filter(anode -> isValidCoord(anode, input.rows, input.cols))
        .count();
  }

  static boolean isValidCoord(Coord coord, int rows, int cols) {
    return (coord.c >=0) && (coord.r >=0) && (coord.c < cols) && (coord.r < rows);
  }

  public record Input(int rows, int cols, Map<Character, List<Coord>> antennas) {}

  public record Coord(int r, int c) {}

  public static Input parse(List<String> input) {
    var rows = input.size();
    var cols = input.getFirst().length();
    var antennas = new HashMap<Character, List<Coord>>();

    for (var row = 0; row < rows; row++) {
      for (var col = 0; col < cols; col++) {
        var freq = input.get(row).charAt(col);
        if (freq != '.') {
          var coords = antennas.getOrDefault(freq, new ArrayList<>());
          coords.add(new Coord(row, col));
          antennas.put(freq, coords);
        }
      }
    }
    return new Input(rows, cols, antennas);
  }
}
