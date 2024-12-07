package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import com.wilb0t.aoc.Day7.Calibration;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Day7Test {

  private static final List<Calibration> TEST_INPUT = Input.TEST.load(Day7::parse);
  private static final List<Calibration> PUZZLE_INPUT = Input.PUZZLE.load(Day7::parse);

  @Test
  public void testGetCalibrationResult_testInput() {
    assertThat(Day7.getCalResult(TEST_INPUT)).isEqualTo(3749L);
  }

  @Test
  public void testGetCalibrationResult_puzzleInput() {
    assertThat(Day7.getCalResult(PUZZLE_INPUT)).isEqualTo(267566105056L);
  }
}
