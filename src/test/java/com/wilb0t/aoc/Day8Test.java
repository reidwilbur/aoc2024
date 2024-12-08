package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Day8Test {

  private static final Day8.Input TEST_INPUT = Input.TEST.parse(Day8::parse);
  private static final Day8.Input PUZZLE_INPUT = Input.PUZZLE.parse(Day8::parse);

  @Test
  public void testGetAntiNodeCount_testInput() {
    assertThat(Day8.getAntiNodeCount(TEST_INPUT)).isEqualTo(14);
  }

  @Test
  public void testGetAntiNodeCount_puzzleInput() {
    assertThat(Day8.getAntiNodeCount(PUZZLE_INPUT)).isEqualTo(285);
  }

  @Test
  public void testGetAntiNodeHarmCount_testInput() {
    assertThat(Day8.getAntiNodeHarmCount(TEST_INPUT)).isEqualTo(34);
  }

  @Test
  public void testGetAntiNodeHarmCount_puzzleInput() {
    assertThat(Day8.getAntiNodeHarmCount(PUZZLE_INPUT)).isEqualTo(944);
  }
}
