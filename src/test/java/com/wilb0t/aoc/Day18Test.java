package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import com.wilb0t.aoc.Day18.Pos;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Day18Test {

  private static final List<Pos> TEST_INPUT = Input.TEST.parse(Day18::parse);
  private static final List<Pos> PUZZLE_INPUT = Input.PUZZLE.parse(Day18::parse);

  @Test
  public void testGetMinSteps_testInput() {
    assertThat(Day18.getMinSteps(TEST_INPUT.subList(0, 12), 7, 7)).isEqualTo(22);
  }

  @Test
  public void testGetMinSteps_puzzleInput() {
    assertThat(Day18.getMinSteps(PUZZLE_INPUT.subList(0, 1024), 71, 71)).isEqualTo(278);
  }
}
