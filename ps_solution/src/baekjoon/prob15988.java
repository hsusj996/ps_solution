package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob15988 {
  static final int MOD = 1_000_000_009;
  static int T;
  static StringBuilder sb = new StringBuilder();
  static int dp[] = new int[1_000_001];

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    dp[0] = 1;
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;

    T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      int n = Integer.parseInt(br.readLine());

      sb.append(dpFunc(n)).append("\n");
    }

    System.out.println(sb.toString());
  }

  private static int dpFunc(int n) {
    if (dp[n] != 0) {
      return dp[n];
    }

    int v1 = dp[n - 1] == 0 ? dp[n - 1] = dpFunc(n - 1) : dp[n - 1];
    int v2 = dp[n - 2] == 0 ? dp[n - 2] = dpFunc(n - 2) : dp[n - 2];
    int v3 = dp[n - 3] == 0 ? dp[n - 3] = dpFunc(n - 3) : dp[n - 3];

    return dp[n] = (((v1 + v2) % MOD) + v3) % MOD;
  }
}
