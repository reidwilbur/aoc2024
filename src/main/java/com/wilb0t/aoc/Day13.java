package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.List;

public class Day13 {

  public static long getTokens(List<Config> configs) {
    return configs.stream()
        .mapToLong(Day13::getTokensForWin)
        .filter(i -> i < Long.MAX_VALUE)
        .sum();
  }

  static long getTokensForWin(Config config) {
    var minTokens = Long.MAX_VALUE;
    for (var apress = 0; apress < 100; apress++) {
      for (var bpress = 0; bpress < 100; bpress++) {
        var xloc = config.ax * apress + config.bx * bpress;
        var yloc = config.ay * apress + config.by * bpress;
        if (xloc == config.px && yloc == config.py) {
          var tokens = apress * 3 + bpress;
          minTokens = Math.min(minTokens, tokens);
        }
      }
    }
    return minTokens;
  }

  public record Config(int ax, int ay, int bx, int by, int px, int py) {
    public static Config from(List<String> lines) {
      if (lines.size() != 3) {
        throw new RuntimeException("Invalid input: " + lines);
      }
      var aparts = lines.getFirst().substring(12).split(",");
      var ax = Integer.parseInt(aparts[0]);
      var ay = Integer.parseInt(aparts[1].substring(3));
      var bparts = lines.get(1).substring(12).split(",");
      var bx = Integer.parseInt(bparts[0]);
      var by = Integer.parseInt(bparts[1].substring(3));
      var pparts = lines.get(2).substring(9).split(",");
      var px = Integer.parseInt(pparts[0]);
      var py = Integer.parseInt(pparts[1].substring(3));
      return new Config(ax, ay, bx, by, px, py);
    }
  }

  public static List<Config> parse(List<String> input) {
    var configLines = new ArrayList<String>();
    var configs = new ArrayList<Config>();
    for (String line : input) {
      if (line.isEmpty()) {
        configs.add(Config.from(configLines));
        configLines.clear();
      } else {
        configLines.add(line);
      }
    }
    return configs;
  }

}
