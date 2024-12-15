package com.wilb0t.aoc;

import java.util.List;

public class Day15 {

  public static long getGpsSum(Input input) {
    var robotPos = input.getRobotPos();
    var map = input.map;
    for (var dir : input.moves) {
      var nextPos = robotPos.next(dir);
      if (map[nextPos.row][nextPos.col] == '.') {
        map[robotPos.row][robotPos.col] = '.';
        map[nextPos.row][nextPos.col] = '@';
        robotPos = nextPos;
      } else if (map[nextPos.row][nextPos.col] == 'O') {
        if (shiftEmptySpace(map, nextPos, dir)) {
          map[robotPos.row][robotPos.col] = '.';
          map[nextPos.row][nextPos.col] = '@';
          robotPos = nextPos;
        }
      }
    }
    return getGpsSum(map);
  }

  static long getGpsSum(char[][] map) {
    long sum = 0;
    for (var row = 0; row < map.length; row++) {
      for (var col = 0; col < map[0].length; col++) {
        if (map[row][col] == 'O') {
          sum += (100L * row) + col;
        }
      }
    }
    return sum;
  }

  static void printMap(char[][] map) {
    for (var row = 0; row < map.length; row++) {
      System.out.println(map[row]);
    }
    System.out.println();
  }

  static boolean shiftEmptySpace(char[][] map, Pos pkgPos, Dir dir) {
    var pos = pkgPos;
    while (map[pos.row][pos.col] != '.' && map[pos.row][pos.col] != '#') {
      pos = pos.next(dir);
    }
    if (map[pos.row][pos.col] == '.') {
      map[pos.row][pos.col] = 'O';
      map[pkgPos.row][pkgPos.col] = '.';
      return true;
    }
    return false;
  }

  public enum Dir { UP, RT, DN, LT }

  public record Pos(int row, int col) {
    public Pos next(Dir dir) {
      return switch (dir) {
        case UP -> new Pos(row - 1, col);
        case RT -> new Pos(row , col + 1);
        case DN -> new Pos(row + 1, col);
        case LT -> new Pos(row, col - 1);
      };
    }
  }

  public record Input(char[][] map, Dir[] moves) {
    public Pos getRobotPos() {
      for (var row = 0; row < map.length; row++) {
        for (var col = 0; col < map[0].length; col++) {
          if (map[row][col] == '@') {
            return new Pos(row, col);
          }
        }
      }
      throw new RuntimeException("No robot found");
    }

    public static Input parse(List<String> input) {
      var split = input.indexOf("");
      var map = new char[split][input.getFirst().length()];
      for (var row = 0; row < split; row++) {
        for (var col = 0; col < map[row].length; col++) {
          map[row][col] = input.get(row).charAt(col);
        }
      }

      var moves = new Dir[input.get(split + 1).length() * (input.size() - (split + 1))];
      var idx = 0;
      for (var line : input.subList(split + 1, input.size())) {
        for (var col = 0; col < line.length(); col++, idx++) {
          switch (line.charAt(col)) {
            case '^' -> moves[idx] = Dir.UP;
            case '>' -> moves[idx] = Dir.RT;
            case 'v' -> moves[idx] = Dir.DN;
            case '<' -> moves[idx] = Dir.LT;
            default -> throw new IllegalArgumentException("Invalid dir: " + line.charAt(idx));
          }
        }
      }
      return new Input(map, moves);
    }
  }
}
