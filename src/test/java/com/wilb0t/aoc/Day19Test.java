package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Day19Test {

  private static final Day19.Input TEST_INPUT = Input.TEST.parse(Day19.Input::parse);
  private static final Day19.Input PUZZLE_INPUT = Input.PUZZLE.parse(Day19.Input::parse);

  @Test
  public void testGetValidDesignCount_testInput() {
    assertThat(Day19.getValidDesignCount(TEST_INPUT)).isEqualTo(6);
  }

  @Test
  public void testGetValidDesignCount_puzzleInput() {
    assertThat(Day19.getValidDesignCount(PUZZLE_INPUT)).isEqualTo(304);
  }
}
