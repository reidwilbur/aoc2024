package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Day14 {

  public static int getSafetyFactor(List<Robot> input, int width, int height) {
    var loLt = 0;
    var hiLt = 0;
    var loRt = 0;
    var hiRt = 0;
    var widthHalf = width / 2;
    var heightHalf = height / 2;
    for (Robot robot : input) {
      var px = ((robot.vx * 100) + robot.px) % width;
      var py = ((robot.vy * 100) + robot.py) % height;
      px = (px < 0) ? width + px : px;
      py = (py < 0) ? height + py : py;
      if (px < widthHalf && py < heightHalf) {
        loLt += 1;
      } else if (px < widthHalf && py > heightHalf) {
        hiLt += 1;
      } else if (px > widthHalf && py < heightHalf) {
        loRt += 1;
      } else if (px > widthHalf && py > heightHalf) {
        hiRt += 1;
      }
    }
    return loLt * hiLt * loRt * hiRt;
  }

  public static int getTreeTime(List<Robot> input, int width, int height) {
    var t = 1;
    // while (!quadsEqual(input, t, width, height)) {
    while (t < 1000000000L) {
      System.out.println(t);
      printRobots(input, t, width, height);
      t += 10000;
    }
    return t;
  }

  static void printRobots(List<Robot> robots, int t, int width, int height) {
    var disp = new char[height][width];
    for (var row = 0; row < height; row++) {
      disp[row] = new char[width];
      Arrays.fill(disp[row], '.');
    }
    for (var robot : robots) {
      var px = ((robot.vx * t) + robot.px) % width;
      var py = ((robot.vy * t) + robot.py) % height;
      px = (px < 0) ? width + px : px;
      py = (py < 0) ? height + py : py;
      disp[py][px] = '*';
    }
    for (var row = 0; row < height; row++) {
      System.out.println(disp[row]);
    }
    System.out.println();
    System.out.println();
  }

  static boolean quadsEqual(List<Robot> input, int t, int width, int height) {
    var loLt = 0;
    var hiLt = 0;
    var loRt = 0;
    var hiRt = 0;
    var widthHalf = width / 2;
    var heightHalf = height / 2;
    for (Robot robot : input) {
      var px = ((robot.vx * t) + robot.px) % width;
      var py = ((robot.vy * t) + robot.py) % height;
      px = (px < 0) ? width + px : px;
      py = (py < 0) ? height + py : py;
      if (px < widthHalf && py < heightHalf) {
        loLt += 1;
      } else if (px < widthHalf && py > heightHalf) {
        hiLt += 1;
      } else if (px > widthHalf && py < heightHalf) {
        loRt += 1;
      } else if (px > widthHalf && py > heightHalf) {
        hiRt += 1;
      }
    }
    return loLt == loRt && hiLt == hiRt;
  }

  static boolean isReflection(List<Robot> input, int t, int width, int height) {
    var widthHalf = width / 2;
    var left = new HashSet<Entry<Integer, Integer>>();
    var right = new HashSet<Entry<Integer, Integer>>();

    for (Robot robot : input) {
      var px = ((robot.vx * t) + robot.px) % width;
      var py = ((robot.vy * t) + robot.py) % height;
      px = (px < 0) ? width + px : px;
      py = (py < 0) ? height + py : py;
      if (px < widthHalf) {
        left.add(Map.entry(px, py));
      } else if (px > widthHalf) {
        right.add(Map.entry(width - px, py));
      }
    }
    return left.equals(right);
  }

  public record Robot(int px, int py, int vx, int vy) {}

  public static List<Robot> parse(List<String> input) {
    var robots = new ArrayList<Robot>();
    for (String line : input) {
      var parts = line.split(" ");
      var posparts = parts[0].substring(2).split(",");
      var velparts = parts[1].substring(2).split(",");
      robots.add(
          new Robot(
              Integer.parseInt(posparts[0]),
              Integer.parseInt(posparts[1]),
              Integer.parseInt(velparts[0]),
              Integer.parseInt(velparts[1])));
    }
    return robots;
  }
}
