package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.List;

public class Day13 {

  public static long getTokens(List<Config> configs) {
    return configs.stream()
        .mapToLong(cfg -> getTokensForWin(cfg, 0))
        .filter(i -> i != Long.MAX_VALUE)
        .sum();
  }

  public static long getTokensWithOfs(List<Config> configs) {
    return configs.stream()
        .mapToLong(cfg -> getTokensForWin(cfg, 10000000000000L))
        .filter(i -> i != Long.MAX_VALUE)
        .sum();
  }

  static long getTokensForWin(Config config, long ofs) {
    long an = (config.px + ofs) * config.by - config.bx * (config.py + ofs);
    long bn = config.ax * (config.py + ofs) - (config.px + ofs) * config.ay;

    long d = (long) config.ax * config.by - (long) config.bx * config.ay;

    if ((an % d == 0L) && (bn % d == 0L)) {
      return (3L * (an / d)) + (bn / d);
    }

    return Long.MAX_VALUE;
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
    configs.add(Config.from(configLines));
    return configs;
  }
}
