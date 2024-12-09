package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Day9Test {

  private static final String TEST_INPUT = "2333133121414131402";
  private static final String PUZZLE_INPUT = Input.PUZZLE.loadStrings().getFirst();

  @Test
  public void testUnpack() {
    assertThat(Day9.unpack("12345"))
        .isEqualTo(new int[]{0,-1,-1,1,1,1,-1,-1,-1,-1,2,2,2,2,2});

    var exp = new int[] {
        0,0,-1,-1,-1,1,1,1,-1,-1,-1,2,-1,-1,-1,3,3,3,-1,4,4,-1,5,5,5,5,-1,6,6,6,6,-1,7,7,7,-1,8,8,8,8,9,9
    };
    assertThat(Day9.unpack(TEST_INPUT)).isEqualTo(exp);
  }

  @Test
  public void testCompactChecksum_testInput() {
    assertThat(Day9.compactChecksum(TEST_INPUT)).isEqualTo(1928);
  }

  @Test
  public void testCompactChecksum_puzzleInput() {
    assertThat(Day9.compactChecksum(PUZZLE_INPUT)).isGreaterThan(1658858242L);
    assertThat(Day9.compactChecksum(PUZZLE_INPUT)).isEqualTo(6461289671426L);
  }
}
