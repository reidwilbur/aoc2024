package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import com.wilb0t.aoc.Day14.Robot;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Day14Test {

  private static final List<Robot> TEST_INPUT = Input.TEST.parse(Day14::parse);
  private static final List<Robot> PUZZLE_INPUT = Input.PUZZLE.parse(Day14::parse);

  @Test
  public void testGetSafetyFactor_testInput() {
    assertThat(Day14.getSafetyFactor(TEST_INPUT, 11, 7)).isEqualTo(12);
  }

  @Test
  public void testGetSafetyFactor_puzzleInput() {
    assertThat(Day14.getSafetyFactor(PUZZLE_INPUT, 101, 103)).isEqualTo(225521010);
  }

  //  @Test
  //  public void testGetTreeTime() {
  //    assertThat(Day14.getTreeTime(PUZZLE_INPUT, 101, 103)).isGreaterThan(1874);
  //  }

  @Test
  public void testParse() {
    assertThat(TEST_INPUT).hasSize(12);
    assertThat(TEST_INPUT.getFirst()).isEqualTo(new Robot(0, 4, 3, -3));
    assertThat(TEST_INPUT.getLast()).isEqualTo(new Robot(9, 5, -3, -3));
  }
}
