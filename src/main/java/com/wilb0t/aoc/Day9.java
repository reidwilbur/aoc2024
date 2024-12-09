package com.wilb0t.aoc;

import java.util.Arrays;

public class Day9 {

  public static long compactChecksum(String input) {
    var unpacked = unpack(input);
    var emptyIdx = nextEmptyIdx(unpacked, 0);
    var endIdx = nextBlockIdx(unpacked, unpacked.length - 1);
    while (emptyIdx < endIdx) {
      unpacked[emptyIdx] = unpacked[endIdx];
      unpacked[endIdx] = -1;
      emptyIdx = nextEmptyIdx(unpacked, emptyIdx + 1);
      endIdx = nextBlockIdx(unpacked, endIdx - 1);
    }
    return checksum(unpacked);
  }

  static long checksum(int[] bmap) {
    var acc = 0L;
    for (var idx = 0; idx < bmap.length; idx++) {
      acc += (bmap[idx] != -1) ? ((long)bmap[idx]) * idx : 0;
    }
    return acc;
  }

  static int nextEmptyIdx(int[] dmap, int ofs) {
    for (var idx = ofs; idx < dmap.length; idx++) {
      if (dmap[idx] == -1) {
        return idx;
      }
    }
    return -1;
  }

  static int nextBlockIdx(int[] dmap, int ofs) {
    for (var idx = ofs; idx >= 0; idx--) {
      if (dmap[idx] != -1) {
        return idx;
      }
    }
    return -1;
  }

  static int[] unpack(String input) {
    var len = 0;
    for (var idx = 0; idx < input.length(); idx++) {
      len += input.charAt(idx) - '0';
    }

    var id = 0;
    var idx = 0;
    int[] unpacked = new int[len];

    for (var iidx = 0; iidx < input.length(); iidx += 2) {
      var blen = input.charAt(iidx) - '0';
      var elen = (iidx + 1 == input.length()) ? 0 : input.charAt(iidx + 1) - '0';
      Arrays.fill(unpacked, idx, idx + blen, id);
      Arrays.fill(unpacked, idx + blen, idx + blen + elen, -1);
      id += 1;
      idx += blen + elen;
    }

    return unpacked;
  }
}
