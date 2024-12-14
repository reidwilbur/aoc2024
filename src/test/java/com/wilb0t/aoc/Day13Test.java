package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day13Test {

  private static final List<Day13.Config> TEST_INPUT = Input.TEST.parse(Day13::parse);
  private static final List<Day13.Config> PUZZLE_INPUT = Input.PUZZLE.parse(Day13::parse);

  @Test
  public void testGetTokens_testInput() {
    assertThat(Day13.getTokens(TEST_INPUT)).isEqualTo(480);
  }

  @Test
  public void testGetTokens_puzzleInput() {
    assertThat(Day13.getTokens(PUZZLE_INPUT)).isEqualTo(28138L);
  }

  @Test
  public void testGetTokensForWin() {
    assertThat(Day13.getTokensForWin(TEST_INPUT.getFirst(), 10000000000000L)).isEqualTo(Long.MAX_VALUE);
    assertThat(Day13.getTokensForWin(TEST_INPUT.get(1), 10000000000000L)).isNotEqualTo(Long.MAX_VALUE);
    assertThat(Day13.getTokensForWin(TEST_INPUT.get(2), 10000000000000L)).isEqualTo(Long.MAX_VALUE);
    assertThat(Day13.getTokensForWin(TEST_INPUT.get(3), 10000000000000L)).isNotEqualTo(Long.MAX_VALUE);
  }

  @Test
  public void testGetTokensWithOfs_puzzleInput() {
    var tokens = Day13.getTokensWithOfs(PUZZLE_INPUT);
    assertThat(tokens).isEqualTo(108394825772874L);
  }
}
