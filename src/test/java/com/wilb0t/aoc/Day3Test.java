package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Day3Test {

  private static final String[] TEST_INPUT = Input.TEST.loadStrings();
  private static final String[] TEST_INPUT2 = new Input("-test2").loadStrings();
  private static final String[] PUZZLE_INPUT = Input.PUZZLE.loadStrings();

  @Test
  public void testGetMulTotal_testInput() {
    assertThat(Day3.getMulTotal(TEST_INPUT)).isEqualTo(161L);
  }

  @Test
  public void testGetMulTotal_puzzleInput() {
    assertThat(Day3.getMulTotal(PUZZLE_INPUT)).isEqualTo(163931492L);
  }

  @Test
  public void testGetMulTotalWithEnables_testInput() {
    assertThat(Day3.getMulTotalWithEnables(TEST_INPUT2)).isEqualTo(48L);
  }

  @Test
  public void testGetMulTotalWithEnables_puzzleInput() {
    assertThat(Day3.getMulTotalWithEnables(PUZZLE_INPUT)).isEqualTo(76911921L);
  }
}
