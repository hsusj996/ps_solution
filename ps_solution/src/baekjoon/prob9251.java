package baekjoon;

import java.io.*;
import java.util.*;

public class prob9251 {
  static String l;
  static String r;
  static int[][] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    l = br.readLine();
    r = br.readLine();

    dp = new int[l.length() + 1][r.length() + 1];
    dp[1][1] = l.charAt(0) == r.charAt(0) ? 1 : 0;

    for (int i = 0; i < l.length(); i++) {
      for (int j = 0; j < r.length(); j++) {
        if (i == 0 && j == 0) {
          continue;
        }

        if (l.charAt(i) == r.charAt(j)) {
          dp[i + 1][j + 1] = dp[i][j] + 1;
        } else {
          dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
        }
      }
    }

    System.out.println(dp[l.length()][r.length()]);
  }

}