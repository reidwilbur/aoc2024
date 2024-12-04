package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day2Test {

  private static final List<List<Integer>> TEST_INPUT = Input.TEST.parse(Day2::parse);
  private static final List<List<Integer>> PUZZLE_INPUT = Input.PUZZLE.parse(Day2::parse);

  @Test
  public void testGetSafeCount_testInput() {
    assertThat(Day2.getSafeCount(TEST_INPUT)).isEqualTo(2);
  }

  @Test
  public void testGetSafeCount_puzzleInput() {
    assertThat(Day2.getSafeCount(PUZZLE_INPUT)).isEqualTo(224);
  }

  @Test
  public void testGetSafeCount_withDamp_testInput() {
    assertThat(Day2.getSafeCountWithDampener(TEST_INPUT)).isEqualTo(4);
  }

  @Test
  public void testGetSafeCount_withDamp_puzzleInput() {
    assertThat(Day2.getSafeCountWithDampener(PUZZLE_INPUT)).isEqualTo(293);
  }
}
