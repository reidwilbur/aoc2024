package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day17Test {

  private static final Day17.Input TEST_INPUT = Input.TEST.parse(Day17::parse);
  private static final Day17.Input PUZZLE_INPUT = Input.PUZZLE.parse(Day17::parse);

  @Test
  public void testCompy() {
    var compy = new Day17.ThreeBitCompy(0, 0, 9, List.of(2, 6));
    compy.run();
    assertThat(compy.regB).isEqualTo(1);
  }

  @Test
  public void testCompy2() {
    var compy = new Day17.ThreeBitCompy(10, 0, 0, List.of(5, 0, 5, 1, 5, 4));
    compy.run();
    assertThat(compy.out).isEqualTo(List.of(0, 1, 2));
  }

  @Test
  public void testCompy3() {
    var compy = new Day17.ThreeBitCompy(2024, 0, 0, List.of(0, 1, 5, 4, 3, 0));
    compy.run();
    assertThat(compy.out).isEqualTo(List.of(4, 2, 5, 6, 7, 7, 7, 7, 3, 1, 0));
    assertThat(compy.regA).isEqualTo(0);
  }

  @Test
  public void testCompy4() {
    var compy = new Day17.ThreeBitCompy(0, 29, 0, List.of(1, 7));
    compy.run();
    assertThat(compy.regB).isEqualTo(26);
  }

  @Test
  public void testCompy5() {
    var compy = new Day17.ThreeBitCompy(0, 2024, 43690, List.of(4, 0));
    compy.run();
    assertThat(compy.regB).isEqualTo(44354);
  }

  @Test
  public void testOutput_testInput() {
    assertThat(Day17.concatOutput(TEST_INPUT)).isEqualTo("4,6,3,5,6,3,5,2,1,0");
  }

  @Test
  public void testOutput_puzzleInput() {
    assertThat(Day17.concatOutput(PUZZLE_INPUT)).isEqualTo("4,0,4,7,1,2,7,1,6");
  }

  @Test
  public void testOutputSame_testInput() {
    var input = new Day17.Input(117440, 0, 0, List.of(0, 3, 5, 4, 3, 0));
    assertThat(Day17.concatOutput(input)).isEqualTo("0,3,5,4,3,0");

    // assertThat(Day17.findRegAVal(input)).isEqualTo(117440);
  }

  @Test
  public void testOutputSame_puzzleInput() {
    // var start = (long)Math.pow(8, 15) + 0b111;
    // var start = (long)Math.pow(8, 15) + (32L) + 0b101L;
    var start = (long) Math.pow(8, 15) + (0b101L << 3 * 15);
    var expOut = PUZZLE_INPUT.mem().stream().map(i -> (long) i).toList();
    var input =
        new Day17.Input(
            start + 0b111, PUZZLE_INPUT.regB(), PUZZLE_INPUT.regC(), PUZZLE_INPUT.mem());
    var out = Day17.getOutput(input);
    assertThat(out).isEqualTo(expOut);
  }
}
