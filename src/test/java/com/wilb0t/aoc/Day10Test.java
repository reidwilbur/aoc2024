package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Day10Test {

  private static final int[][] TEST_INPUT = Input.TEST.loadIntGrid();
  private static final int[][] TEST_INPUT2 = new Input("-test2").loadIntGrid();
  private static final int[][] PUZZLE_INPUT = Input.PUZZLE.loadIntGrid();

  @Test
  public void testGetTrailheadScore_testInput() {
    assertThat(Day10.getTrailheadScore(TEST_INPUT)).isEqualTo(1);
    assertThat(Day10.getTrailheadScore(TEST_INPUT2)).isEqualTo(36);
  }

  @Test
  public void testGetTrailheadScore_puzzleInput() {
    assertThat(Day10.getTrailheadScore(PUZZLE_INPUT)).isEqualTo(587);
  }

  @Test
  public void testGetTrailheadHikingScore_testInput() {
    assertThat(Day10.getTrailheadHikingScore(TEST_INPUT2)).isEqualTo(81);
  }

  @Test
  public void testGetTrailheadHikingScore_puzzleInput() {
    assertThat(Day10.getTrailheadHikingScore(PUZZLE_INPUT)).isEqualTo(1340);
  }
}
