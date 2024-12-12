package com.wilb0t.aoc;

import static org.assertj.core.api.Assertions.assertThat;

import com.wilb0t.aoc.Day12.Coord;
import org.junit.jupiter.api.Test;

public class Day12Test {

  private static final char[][] TEST_INPUT = Input.TEST.loadCharGrid();
  private static final char[][] TEST_INPUT2 = new Input("-test2").loadCharGrid();
  private static final char[][] TEST_INPUT3 = new Input("-test3").loadCharGrid();
  private static final char[][] PUZZLE_INPUT = Input.PUZZLE.loadCharGrid();

  @Test
  public void testGetFenceCost_testInput() {
    assertThat(Day12.getFenceCost(TEST_INPUT)).isEqualTo(140);
    assertThat(Day12.getFenceCost(TEST_INPUT2)).isEqualTo(772);
    assertThat(Day12.getFenceCost(TEST_INPUT3)).isEqualTo(1930);
  }

  @Test
  public void testGetFenceCost_puzzleInput() {
    assertThat(Day12.getFenceCost(PUZZLE_INPUT)).isEqualTo(1451030L);
  }

  @Test
  public void testGetRegion() {
    var region = Day12.getRegion(new Coord(0, 0), TEST_INPUT);
    assertThat(region).hasSize(4);
    assertThat(region.stream().map(c -> TEST_INPUT[c.r()][c.c()])).allMatch(n -> n == 'A');

    region = Day12.getRegion(new Coord(1, 0), TEST_INPUT);
    assertThat(region).hasSize(4);
    assertThat(region.stream().map(c -> TEST_INPUT[c.r()][c.c()])).allMatch(n -> n == 'B');

    region = Day12.getRegion(new Coord(1, 2), TEST_INPUT);
    assertThat(region).hasSize(4);
    assertThat(region.stream().map(c -> TEST_INPUT[c.r()][c.c()])).allMatch(n -> n == 'C');

    region = Day12.getRegion(new Coord(1, 3), TEST_INPUT);
    assertThat(region).hasSize(1);
    assertThat(region.stream().map(c -> TEST_INPUT[c.r()][c.c()])).allMatch(n -> n == 'D');

    region = Day12.getRegion(new Coord(3, 0), TEST_INPUT);
    assertThat(region).hasSize(3);
    assertThat(region.stream().map(c -> TEST_INPUT[c.r()][c.c()])).allMatch(n -> n == 'E');
  }
}
