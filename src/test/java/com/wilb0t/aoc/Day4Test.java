package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Day4Test {

  private static final String[] TEST_INPUT = Input.TEST.loadStrings();
  private static final String[] PUZZLE_INPUT = Input.PUZZLE.loadStrings();

  @Test
  public void testGetXmasCount_testInput() {
    assertThat(Day4.getXmasCount(TEST_INPUT)).isEqualTo(18);
  }

  @Test
  public void testGetXmasCount_puzzleInput() {
    assertThat(Day4.getXmasCount(PUZZLE_INPUT)).isEqualTo(2406);
  }

  @Test
  public void testGetXXmasCount_testInput() {
    assertThat(Day4.getXXmasCount(TEST_INPUT)).isEqualTo(9);
  }

  @Test
  public void testGetXXmasCount_puzzleInput() {
    assertThat(Day4.getXXmasCount(PUZZLE_INPUT)).isEqualTo(1807);
  }

  @Test
  public void testGetXmasLineCount() {
    assertThat(Day4.getXmasLineCount("XXMASS")).isEqualTo(1);
    assertThat(Day4.getXmasLineCount("XXMASSSAMXS")).isEqualTo(2);
  }

  @Test
  public void testGetDiagLines() {
    var input = List.of(
        "..X...",
        ".SAMX.",
        ".A..A.",
        "XMAS.S",
        ".X....");

    var exp = Arrays.asList(
        ".S.S.",
        ".AA.",
        ".M.",
        "XX",
        ".",
        ".A...",
        "XMAS",
        ".X.",
        "..",
        ".",
        ".M.M.",
        ".AAX",
        "XS.",
        "..",
        ".",
        ".X.AX",
        ".AS.",
        "...",
        "S.",
        ".");

    assertThat(Day4.getDiagLines(input.toArray(new String[0]))).isEqualTo(exp);
  }

  @Test
  public void testGetXmasCount_testInput2() {
    var input = List.of(
        "..X...",
        ".SAMX.",
        ".A..A.",
        "XMAS.S",
        ".X....");
    assertThat(Day4.getXmasCount(input.toArray(new String[0]))).isEqualTo(4);
  }
}
