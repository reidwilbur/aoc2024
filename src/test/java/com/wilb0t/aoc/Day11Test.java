package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day11Test {

  private static final List<String> TEST_INPUT = Day11.parse(List.of("0 1 10 99 999"));
  private static final List<String> TEST_INPUT2 = Day11.parse(List.of("125 17"));
  private static final List<String> PUZZLE_INPUT =
      Day11.parse(List.of("4610211 4 0 59 3907 201586 929 33750"));

  @Test
  public void testGetStoneCount_testInput() {
    assertThat(Day11.getStoneCount(1, TEST_INPUT)).isEqualTo(7);
    assertThat(Day11.getStoneCount(6, TEST_INPUT2)).isEqualTo(22);
    assertThat(Day11.getStoneCount(25, TEST_INPUT2)).isEqualTo(55312);
  }

  @Test
  public void testGetStoneCount_puzzleInput() {
    var count = Day11.getStoneCount(25, PUZZLE_INPUT);
    assertThat(count).isEqualTo(197357);
  }

  @Test
  public void testGetStoneCount_puzzleInput_75rounds() {
    var count = Day11.getStoneCount(75, PUZZLE_INPUT);
    assertThat(count).isEqualTo(234568186890978L);
  }
}
