package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day17 {

  public static String concatOutput(Input input) {
    var compy = new ThreeBitCompy(input.regA, input.regB, input.regC, input.mem);
    compy.run();
    return compy.out.stream().map(Object::toString).collect(Collectors.joining(","));
  }

  static class ThreeBitCompy {
    int regA;
    int regB;
    int regC;
    int ip = 0;
    List<Integer> mem;
    List<Integer> out;

    public ThreeBitCompy(int regA, int regB, int regC, List<Integer> mem) {
      this.regA = regA;
      this.regB = regB;
      this.regC = regC;
      this.mem = mem;
      out = new ArrayList<>();
    }

    public void run() {
      while (ip < mem.size()) {
        var opcode = mem.get(ip);
        var operand = mem.get(ip + 1);
        ip += 2;
        switch (opcode) {
          case 0 -> {
            var comboVal = getComboValue(operand);
            var div = 1;
            div = div << comboVal;
            regA = regA / div;
          }
          case 1 -> {
            // check something about bit length here?
            regB = regB ^ operand;
          }
          case 2 -> {
            regB = getComboValue(operand) % 8;
          }
          case 3 -> {
            if (regA != 0) {
              ip = operand;
            }
          }
          case 4 -> {
            regB = regC ^ regB;
          }
          case 5 -> {
            out.add(getComboValue(operand) % 8);
          }
          case 6 -> {
            var comboVal = getComboValue(operand);
            var div = 1;
            div = div << comboVal;
            regB = regA / div;
          }
          case 7 -> {
            var comboVal = getComboValue(operand);
            var div = 1;
            div = div << comboVal;
            regC = regA / div;
          }
        }
      }
    }

    int getComboValue(int comboOperand) {
      return switch(comboOperand) {
        case int n when n >= 0 && n <= 3 -> n;
        case 4 -> regA;
        case 5 -> regB;
        case 6 -> regC;
        default -> throw new IllegalStateException("Invalid combo operand: " + comboOperand);
      };
    }
  }

  public record Input(int regA, int regB, int regC, List<Integer> mem) {}

  public static Input parse(List<String> input) {
    var regA = Integer.parseInt(input.getFirst().split(": ")[1]);
    var regB = Integer.parseInt(input.get(1).split(": ")[1]);
    var regC = Integer.parseInt(input.get(2).split(": ")[1]);

    var mem = Arrays.stream(input.getLast().split(": ")[1].split(",")).map(Integer::parseInt).toList();
    return new Input(regA, regB, regC, mem);
  }
}
