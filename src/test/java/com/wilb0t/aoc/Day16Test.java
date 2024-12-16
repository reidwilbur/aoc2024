package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Day16Test {
  private static final char[][] TEST_INPUT = Input.TEST.loadCharGrid();
  private static final char[][] TEST_INPUT2 = new Input("-test2").loadCharGrid();
  private static final char[][] PUZZLE_INPUT = Input.PUZZLE.loadCharGrid();

  @Test
  public void testGetLowestPath_testInput() {
    assertThat(Day16.getLowestPath(TEST_INPUT)).isEqualTo(7036);
    assertThat(Day16.getLowestPath(TEST_INPUT2)).isEqualTo(11048);
  }

  @Test
  public void testGetLowestPath_puzzleInput() {
    assertThat(Day16.getLowestPath(PUZZLE_INPUT)).isEqualTo(  115500L);
  }
}
