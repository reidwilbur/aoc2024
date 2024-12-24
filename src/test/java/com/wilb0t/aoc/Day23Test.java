package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class Day23Test {

  private static final Map<String, Set<String>> TEST_INPUT = Input.TEST.parse(Day23::parse);
  private static final Map<String, Set<String>> PUZZLE_INPUT = Input.PUZZLE.parse(Day23::parse);

  @Test
  public void testGetHistorianLanGames_testInput() {
    assertThat(Day23.getHistorianLanGames(TEST_INPUT)).isEqualTo(7);
  }

  @Test
  public void testGetHistorianLanGames_puzzleInput() {
    assertThat(Day23.getHistorianLanGames(PUZZLE_INPUT)).isEqualTo(1253);
  }

  @Test
  public void testGetLanPassword_testInput() {
    assertThat(Day23.getLanPassword(TEST_INPUT)).isEqualTo("co,de,ka,ta");
  }

  @Test
  public void testGetLanPassword_puzzleInput() {
    assertThat(Day23.getLanPassword(PUZZLE_INPUT))
        .isEqualTo("ag,bt,cq,da,hp,hs,mi,pa,qd,qe,qi,ri,uq");
  }
}
