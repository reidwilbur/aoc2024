package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Day6Test {

  private static final Day6.Input TEST_INPUT = Input.TEST.parse(Day6::parse);
  private static final Day6.Input PUZZLE_INPUT = Input.PUZZLE.parse(Day6::parse);

  @Test
  public void testGetPositionCount_testInput() {
    assertThat(Day6.getPositionCount(TEST_INPUT)).isEqualTo(41);
  }

  @Test
  public void testGetPositionCount_puzzleInput() {
    assertThat(Day6.getPositionCount(PUZZLE_INPUT)).isEqualTo(4964);
  }
}
