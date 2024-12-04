package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Day1Test {

  private static final Day1.Input TEST_INPUT = Input.TEST.parse(Day1::parse);
  private static final Day1.Input PUZZLE_INPUT = Input.PUZZLE.parse(Day1::parse);

  @Test
  void testGetDist_testInput() {
    assertThat(Day1.getDist(TEST_INPUT)).isEqualTo(11);
  }

  @Test
  void testGetDist_puzzleInput() {
    assertThat(Day1.getDist(PUZZLE_INPUT)).isEqualTo(2815556);
  }

  @Test
  void testGetSimilarity_testInput() {
    assertThat(Day1.getSimilarity(TEST_INPUT)).isEqualTo(31);
  }

  @Test
  void testGetSimilarity_puzzleInput() {
    assertThat(Day1.getSimilarity(PUZZLE_INPUT)).isEqualTo(23927637);
  }
}
