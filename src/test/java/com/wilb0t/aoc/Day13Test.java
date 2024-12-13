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
  public void testGetTokens_puzzlenput() {
    assertThat(Day13.getTokens(PUZZLE_INPUT)).isEqualTo(28138L);
  }
}
