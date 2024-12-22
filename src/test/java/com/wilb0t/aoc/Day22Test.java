package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Day22Test {

  private static final int[] TEST_INPUT = Input.TEST.loadInts();
  private static final int[] PUZZLE_INPUT = Input.PUZZLE.loadInts();

  @Test
  public void testGetSecretNumSum_testInput() {
    assertThat(Day22.getSecretNumSum(TEST_INPUT)).isEqualTo(37327623L);
  }

  @Test
  public void testGetSecretNumSum_puzzleInput() {
    assertThat(Day22.getSecretNumSum(PUZZLE_INPUT)).isEqualTo(15335183969L);
  }

  @Test
  public void testGetNextNum() {
    assertThat(Day22.getNextNum(123)).isEqualTo(15887950);
    assertThat(Day22.getNextNum(15887950)).isEqualTo(16495136);
    assertThat(Day22.getNextNum(16495136)).isEqualTo(527345);
  }
}
