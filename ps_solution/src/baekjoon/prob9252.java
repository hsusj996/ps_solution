package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class prob9252 {
  static String s1, s2;
  static int size1, size2;
  static int[][] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    s1 = br.readLine();
    s2 = br.readLine();

    size1 = s1.length();
    size2 = s2.length();

    dp = new int[size1 + 1][size2 + 1];

    for (int i = 0; i < size1; i++) {
      for (int j = 0; j < size2; j++) {
        if (s1.charAt(i) == s2.charAt(j)) {
          dp[i + 1][j + 1] = dp[i][j] + 1;
        } else {
          dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
        }
      }
    }

    // 공통 수열 뽑아내기
    String lcs = LCS();

    System.out.println(dp[size1][size2]);
    System.out.println(lcs);
  }

  private static String LCS() {
    Stack<Character> stk = new Stack<>();
    int x = size1;
    int y = size2;
    while (dp[x][y] > 0) {
      int cur = dp[x][y];

      if (cur == dp[x - 1][y]) {
        x--;
        continue;
      }
      if (cur == dp[x][y - 1]) {
        y--;
        continue;
      }

      stk.add(s1.charAt(x - 1));
      x--;
      y--;
    }

    StringBuilder ret = new StringBuilder();
    while (!stk.isEmpty()) {
      ret.append(stk.pop());
    }

    return ret.toString();
  }
}
