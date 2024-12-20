package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map.Entry;
import org.junit.jupiter.api.Test;

public class Day20Test {

  private static final char[][] TEST_INPUT = Input.TEST.loadCharGrid();
  private static final char[][] PUZZLE_INPUT = Input.PUZZLE.loadCharGrid();

  @Test
  public void testGetCheats_testInput() {
    var timeCounts = Day20.getCheats(TEST_INPUT);
    assertThat(timeCounts.get(64)).isEqualTo(1);
    assertThat(timeCounts.get(40)).isEqualTo(1);
    assertThat(timeCounts.get(38)).isEqualTo(1);
    assertThat(timeCounts.get(36)).isEqualTo(1);
    assertThat(timeCounts.get(20)).isEqualTo(1);
    assertThat(timeCounts.get(12)).isEqualTo(3);
    assertThat(timeCounts.get(10)).isEqualTo(2);
    assertThat(timeCounts.get(8)).isEqualTo(4);
    assertThat(timeCounts.get(6)).isEqualTo(2);
    assertThat(timeCounts.get(4)).isEqualTo(14);
    assertThat(timeCounts.get(2)).isEqualTo(14);
  }

  @Test
  public void testGetCheats_puzzleInput() {
    var timeCounts = Day20.getCheats(PUZZLE_INPUT);
    var cheatsAtLeast100 =
        timeCounts.entrySet().stream()
            .filter(e -> e.getKey() >= 100)
            .mapToLong(Entry::getValue)
            .sum();
    assertThat(cheatsAtLeast100).isEqualTo(1409L);
  }

  @Test
  public void testGetLongCheats_testInput() {
    var timeCounts = Day20.getLongCheats(TEST_INPUT);
    assertThat(timeCounts.get(76)).isEqualTo(3);
    assertThat(timeCounts.get(74)).isEqualTo(4);
    assertThat(timeCounts.get(52)).isEqualTo(31);
    assertThat(timeCounts.get(50)).isEqualTo(32);
  }

  @Test
  public void testGetLongCheats_puzzleInput() {
    var timeCounts = Day20.getLongCheats(PUZZLE_INPUT);
    var cheatsAtLeast100 =
        timeCounts.entrySet().stream()
            .filter(e -> e.getKey() >= 100)
            .mapToLong(Entry::getValue)
            .sum();
    assertThat(cheatsAtLeast100).isEqualTo(1012821L);
  }

  @Test
  public void testGetPath() {
    var path = Day20.getPath(TEST_INPUT);
    assertThat(path.time().get(path.end())).isEqualTo(84);
  }
}
