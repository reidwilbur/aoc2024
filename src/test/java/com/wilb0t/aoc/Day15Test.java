package com.wilb0t.aoc;

import static com.wilb0t.aoc.Day15.Dir.DN;
import static com.wilb0t.aoc.Day15.Dir.LT;
import static com.wilb0t.aoc.Day15.Dir.RT;
import static com.wilb0t.aoc.Day15.Dir.UP;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Day15Test {
  private static final Day15.Input TEST_INPUT = Input.TEST.parse(Day15.Input::parse);
  private static final Day15.Input TEST_INPUT2 = new Input("-test2").parse(Day15.Input::parse);
  private static final Day15.Input PUZZLE_INPUT = Input.PUZZLE.parse(Day15.Input::parse);

  @Test
  public void testParse() {
    assertThat(TEST_INPUT.map()).hasDimensions(10, 10);
    assertThat(TEST_INPUT.moves()).startsWith(LT, DN, DN, RT);
    assertThat(TEST_INPUT.moves()).endsWith(UP, LT, LT, UP);
  }

  @Test
  public void testGetGpsSum_testInput() {
    assertThat(Day15.getGpsSum(TEST_INPUT2)).isEqualTo(2028);
    assertThat(Day15.getGpsSum(TEST_INPUT)).isEqualTo(10092);
  }

  @Test
  public void testGetGpsSum_puzzleInput() {
    assertThat(Day15.getGpsSum(PUZZLE_INPUT)).isEqualTo(1441031L);
  }
}
