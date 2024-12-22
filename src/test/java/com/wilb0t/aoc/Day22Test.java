package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day22Test {

  private static final int[] TEST_INPUT = Input.TEST.loadInts();
  private static final int[] TEST_INPUT2 = new Input("-test2").loadInts();
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
  public void testGetMaxBananas_testInput() {
    assertThat(Day22.getMaxBananas(TEST_INPUT2)).isEqualTo(23L);
  }

  @Test
  public void testGetMaxBananas_puzzleInput() {
    assertThat(Day22.getMaxBananas(PUZZLE_INPUT)).isEqualTo(1696L);
  }

  @Test
  public void testGetNextNum() {
    assertThat(Day22.getNextNum(123)).isEqualTo(15887950);
    assertThat(Day22.getNextNum(15887950)).isEqualTo(16495136);
    assertThat(Day22.getNextNum(16495136)).isEqualTo(527345);
  }

  @Test
  public void testMonkey() {
    var monkey = Day22.Monkey.of(123, 10);
    assertThat(monkey.seqVals()).hasSize(6);
  }
}
