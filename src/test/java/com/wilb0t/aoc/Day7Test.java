package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import com.wilb0t.aoc.Day7.Calibration;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Day7Test {

  private static final List<Calibration> TEST_INPUT = Input.TEST.load(Day7::parse);
  private static final List<Calibration> PUZZLE_INPUT = Input.PUZZLE.load(Day7::parse);

  @Test
  public void testGetCalResult_testInput() {
    assertThat(Day7.getCalResult(TEST_INPUT)).isEqualTo(3749L);
  }

  @Test
  public void testGetCalResult_puzzleInput() {
    assertThat(Day7.getCalResult(PUZZLE_INPUT)).isEqualTo(267566105056L);
  }

  @Test
  public void testGetCalResultConcat_testInput() {
    assertThat(Day7.getCalResultConcat(TEST_INPUT)).isEqualTo(11387L);
  }

  @Test
  public void testGetCalResultConcat_puzzleInput() {
    //assertThat(Day7.getCalResultConcat(PUZZLE_INPUT)).isLessThan(116161449641293L);
    assertThat(Day7.getCalResultConcat(PUZZLE_INPUT)).isLessThan(116159647288295L);
  }
}
