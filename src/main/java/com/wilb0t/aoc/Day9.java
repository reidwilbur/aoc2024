package com.wilb0t.aoc;

import java.util.Arrays;

public class Day9 {

  public static long compactChecksum(String input) {
    var unpacked = unpack(input);
    var emptyIdx = nextEmptyIdx(unpacked, 0);
    var endIdx = prevBlockIdx(unpacked, unpacked.length - 1);
    while (emptyIdx < endIdx) {
      unpacked[emptyIdx] = unpacked[endIdx];
      unpacked[endIdx] = -1;
      emptyIdx = nextEmptyIdx(unpacked, emptyIdx + 1);
      endIdx = prevBlockIdx(unpacked, endIdx - 1);
    }
    return checksum(unpacked);
  }

  public static long compactChecksumDefrag(String input) {
    var unpacked = unpack(input);
    var maxId = getMaxId(unpacked);
    var lastFileOfs = unpacked.length - 1;

    for (var id = maxId; id > 0; id--) {
      var file = getFile(unpacked, lastFileOfs, id);
      lastFileOfs = file.ofs;
      var freeOfs = findFreeSpaceForFile(unpacked, file);
      if (freeOfs != -1) {
        Arrays.fill(unpacked, freeOfs, freeOfs + file.len, file.id);
        Arrays.fill(unpacked, file.ofs, file.ofs + file.len, -1);
      }
    }
    return checksum(unpacked);
  }

  static int findFreeSpaceForFile(int[] dmap, File file) {
    var start = 0;
    while (start < file.ofs) {
      if (dmap[start] == -1) {
        var end = start + 1;
        while (end < file.ofs && dmap[end] == -1) {
          end += 1;
        }
        if (end - start >= file.len) {
          return start;
        }
        start = end + 1;
      } else {
        start += 1;
      }
    }
    return -1;
  }

  static long checksum(int[] bmap) {
    var acc = 0L;
    for (var idx = 0; idx < bmap.length; idx++) {
      long id = bmap[idx];
      acc += (bmap[idx] == -1) ? 0 : id * idx;
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

  static int prevBlockIdx(int[] dmap, int ofs) {
    for (var idx = ofs; idx >= 0; idx--) {
      if (dmap[idx] != -1) {
        return idx;
      }
    }
    return -1;
  }

  static File getFile(int[] dmap, int ofs, int id) {
    var end = -1;
    for (var idx = ofs; idx > 0 && end == -1; idx--) {
      if (dmap[idx] == id) {
        end = idx;
      }
    }
    var start = 0;
    for (var idx = end - 1; idx >= 0 && start == 0; idx--) {
      if (dmap[idx] != id) {
        start = idx + 1;
      }
    }
    if (end != -1) {
      return new File(id, start, end - start + 1);
    }
    throw new RuntimeException("no block found: " + id);
  }

  static int getMaxId(int[] dmap) {
    for (var idx = dmap.length - 1; idx >= 0; idx--) {
      if (dmap[idx] != -1) {
        return dmap[idx];
      }
    }
    return -1;
  }

  record File(int id, int ofs, int len) {}

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
      idx += blen;
      Arrays.fill(unpacked, idx, idx + elen, -1);
      idx += elen;
      id += 1;
    }

    return unpacked;
  }
}
