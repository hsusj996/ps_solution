package baekjoon;

import java.io.*;
import java.util.*;

public class prob2011 {
  static long[] dp;
  static int prev;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String s = br.readLine();
    dp = new long[s.length() + 1];

    dp[0] = 1;
    dp[1] = 1;
    prev = s.charAt(0) - '0';
    if (prev == 0) {
      System.out.println(0);
      return;
    }

    for (int i = 1; i < s.length(); i++) {
      int now = s.charAt(i) - '0';
      if (now >= 1 && now <= 9) {
        dp[i + 1] = dp[i];
      }

      long j = 0;
      if (prev == 1) {
        j = dp[i - 1];
      }
      if (prev == 2) {
        if (now <= 6) {
          j = dp[i - 1];
        }
      }
      prev = s.charAt(i) - '0';
      dp[i + 1] += j;
      dp[i + 1] %= 1000000;
    }
    System.out.println(dp[s.length()]);
  }
}
