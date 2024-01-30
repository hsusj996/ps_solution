package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class prob1935 {
  static int N;
  static String s;
  static int[] operand = new int[26];
  static Stack<Double> stack = new Stack<>();
  static double ans;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    s = br.readLine();

    for (int i = 0; i < N; i++) {
      operand[i] = Integer.parseInt(br.readLine());
    }

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == '*') {
        double op1 = stack.pop();
        double op2 = stack.pop();
        stack.add(op2 * op1);
      } else if (c == '/') {
        double op1 = stack.pop();
        double op2 = stack.pop();
        stack.add(op2 / op1);
      } else if (c == '+') {
        double op1 = stack.pop();
        double op2 = stack.pop();
        stack.add(op2 + op1);
      } else if (c == '-') {
        double op1 = stack.pop();
        double op2 = stack.pop();
        stack.add(op2 - op1);
      } else {
        stack.add((double) operand[c - 'A']);
      }
    }

    System.out.printf("%.2f", stack.pop());
  }
}
