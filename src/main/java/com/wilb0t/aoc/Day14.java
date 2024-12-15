package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.List;

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
    while (!quadsEqual(input, t, width, height)) {
      t += 1;
    }
    return t;
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
