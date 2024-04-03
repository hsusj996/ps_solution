package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    dfs(0, 0, "");

    System.out.println(dp[size1][size2]);
  }

  private static void dfs(int x, int y, String lcs) {
    for (int i = x; i <= size1; i++) {
      for (int j = y; j <= size2; j++) {

      }
    }
  }
}
