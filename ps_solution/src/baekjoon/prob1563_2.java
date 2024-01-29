package baekjoon;

import java.io.*;
import java.util.*;

public class prob1563_2 {
  static int N;
  static int[][][] DP;
  static final int MOD = 1000000;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    DP = new int[N + 1][3][2];

    DP[1][0][0] = 1;
    DP[1][0][1] = 1;
    DP[1][1][0] = 1;

    for (int i = 2; i <= N; i++) {
      DP[i][0][0] = (DP[i - 1][0][0] + DP[i - 1][1][0] + DP[i - 1][2][0]) % MOD;
      DP[i][0][1] = (DP[i - 1][0][0] + DP[i - 1][1][0] + DP[i - 1][2][0] + DP[i - 1][0][1] + DP[i - 1][1][1]
          + DP[i - 1][2][1]) % MOD;
      DP[i][1][0] = DP[i - 1][0][0] % MOD;
      DP[i][1][1] = DP[i - 1][0][1] % MOD;
      DP[i][2][0] = DP[i - 1][1][0] % MOD;
      DP[i][2][1] = DP[i - 1][1][1] % MOD;
    }

    int sum = DP[N][0][0] + DP[N][0][1] + DP[N][1][0] + DP[N][1][1] + DP[N][2][0] + DP[N][2][1];

    System.out.println(sum % MOD);
  }
}