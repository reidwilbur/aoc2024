package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Day4 {

  private static final Pattern XMASPAT = Pattern.compile("XMAS");
  private static final Pattern SAMXPAT = Pattern.compile("SAMX");

  public static int getXmasCount(List<String> lines) {
    var rows = lines.size();
    var cols = lines.getFirst().length();

    int count = 0;
    // rows
    for (String line : lines) {
      count += getXmasLineCount(line);
    }
    // cols
    for (var col = 0; col < cols; col++) {
      var sb = new StringBuilder();
      for (var row = 0; row < rows; row++) {
        sb.append(lines.get(row).charAt(col));
      }
      count += getXmasLineCount(sb.toString());
    }
    // diags
    var diags = getDiagLines(lines);
    for (var diag : diags) {
      var cc = getXmasLineCount(diag);
      count += cc;
    }

    return count;
  }

  static List<String> getDiagLines(List<String> lines) {
    var rows = lines.size();
    var cols = lines.getFirst().length();

    // left diag bottom
    var diagRows = new ArrayList<String>();
    for (var row = 0; row < rows; row++) {
      var sb = new StringBuilder();
      for (int col = 0, rr = row; col < cols && rr < rows; col++, rr++) {
        sb.append(lines.get(rr).charAt(col));
      }
      diagRows.add(sb.toString());
    }
    // left diag top
    for (var col = 1; col < cols; col++) {
      var sb = new StringBuilder();
      for (int cc = col, row = 0; cc < cols && row < rows; cc++, row++) {
        sb.append(lines.get(row).charAt(cc));
      }
      diagRows.add(sb.toString());
    }
    // right diag top
    for (var col = cols - 2; col >= 0; col--) {
      var sb = new StringBuilder();
      for (int cc = col, row = 0; cc >= 0 && row < rows; cc--, row++) {
        sb.append(lines.get(row).charAt(cc));
      }
      diagRows.add(sb.toString());
    }
    // right diag bottom
    for (var row = 0; row < rows; row++) {
      var sb = new StringBuilder();
      for (int col = cols - 1, rr = row; col >= 0 && rr < rows; col--, rr++) {
        sb.append(lines.get(rr).charAt(col));
      }
      diagRows.add(sb.toString());
    }
    return diagRows;
  }

  static int getXmasLineCount(String line) {
    int count = 0;
    for (var matcher : List.of(XMASPAT.matcher(line), SAMXPAT.matcher(line))) {
      while (matcher.find()) {
        count += 1;
      }
    }
    return count;
  }

  public static int getXXmasCount(List<String> lines) {
    var rows = lines.size();
    var cols = lines.get(0).length();

    var count = 0;
    for (var row = 1; row < rows - 1; row++) {
      for (var col = 1; col < cols - 1; col++) {
        if (lines.get(row).charAt(col) == 'A') {
          count += (isX(row, col, lines)) ? 1 : 0;
        }
      }
    }

    return count;
  }

  public static boolean isX(int row, int col, List<String> lines) {
    var rightDiag =
        ((lines.get(row + 1).charAt(col - 1) == 'M') && (lines.get(row - 1).charAt(col + 1) == 'S'))
            || ((lines.get(row + 1).charAt(col - 1) == 'S')
                && (lines.get(row - 1).charAt(col + 1) == 'M'));
    var leftDiag =
        ((lines.get(row + 1).charAt(col + 1) == 'M') && (lines.get(row - 1).charAt(col - 1) == 'S'))
            || ((lines.get(row + 1).charAt(col + 1) == 'S')
                && (lines.get(row - 1).charAt(col - 1) == 'M'));
    return rightDiag && leftDiag;
  }
}
