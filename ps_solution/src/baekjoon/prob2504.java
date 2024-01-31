package baekjoon;

import java.util.Scanner;
import java.util.Stack;

public class prob2504 {
  static Stack<String> stk = new Stack<>();
  static String s;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    s = sc.nextLine();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == '(' || c == '[') {
        stk.add(Character.toString(c));
        continue;
      }

      if (c == ')') {
        int n = 0;
        while (true) {
          if (stk.isEmpty()) {
            System.out.println(0);
            return;
          }

          String s = stk.pop();
          if (s.equals("[")) {
            System.out.println(0);
            return;
          } else if (s.equals("(")) {
            if (n == 0) {
              stk.add("2");
            } else {
              stk.add(Integer.toString(n * 2));
            }
            break;
          } else {
            n += Integer.parseInt(s);
          }
        }
      }

      if (c == ']') {
        int n = 0;
        while (true) {
          if (stk.isEmpty()) {
            System.out.println(0);
            return;
          }

          String s = stk.pop();
          if (s.equals("(")) {
            System.out.println(0);
            return;
          } else if (s.equals("[")) {
            if (n == 0) {
              stk.add("3");
            } else {
              stk.add(Integer.toString(n * 3));
            }
            break;
          } else {
            n += Integer.parseInt(s);
          }
        }
      }
    }

    int sum = 0;
    while (!stk.isEmpty()) {
      String s = stk.pop();
      if (s.equals("(") || s.equals("[")) {
        System.out.println(0);
        return;
      }

      sum += Integer.parseInt(s);
    }
    System.out.println(sum);
    sc.close();
  }
}
