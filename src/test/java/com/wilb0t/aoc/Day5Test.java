package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day5Test {

  private static final Day5.Input TEST_INPUT = Input.TEST.parse(Day5::parse);
  private static final Day5.Input PUZZLE_INPUT = Input.PUZZLE.parse(Day5::parse);

  @Test
  public void testGetValidMiddleSum_testInput() {
    assertThat(Day5.getValidMiddleSum(TEST_INPUT)).isEqualTo(143);
  }

  @Test
  public void testGetValidMiddleSum_puzzleInput() {
    assertThat(Day5.getValidMiddleSum(PUZZLE_INPUT)).isEqualTo(4609);
  }

  @Test
  public void testParse() {
    assertThat(TEST_INPUT.ordering().get(47)).containsOnly(53, 13, 61, 29);
    assertThat(TEST_INPUT.ordering().get(53)).containsOnly(29, 13);

    assertThat(TEST_INPUT.updates().getFirst()).isEqualTo(List.of(75,47,61,53,29));
    assertThat(TEST_INPUT.updates().getLast()).isEqualTo(List.of(97,13,75,29,47));
  }
}
