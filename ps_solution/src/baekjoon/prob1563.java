package baekjoon;

import java.io.*;
import java.util.*;

public class prob1563 {
  static int N;
  static int[] DP;
  static int[] DP_NoLate;
  static final int MOD = 1000000;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    DP = new int[N + 1];
    DP_NoLate = new int[N + 1];

    DP[0] = 1;
    DP_NoLate[0] = 1;
    DP[1] = 3;
    DP_NoLate[1] = 2;
    DP[2] = 6;
    DP_NoLate[2] = 4;
    DP[3] = 19;
    DP_NoLate[3] = 7;

    for (int i = 4; i <= N; i++) {
      DP_NoLate[i] = 2 * DP_NoLate[i - 1] - DP_NoLate[i - 4];
      DP_NoLate[i] %= MOD;
    }

    for (int i = 4; i <= N; i++) {
      DP[i] = DP[i - 1] + DP_NoLate[i - 1] + DP[i - 1] - 2 * DP[i - 4];
      DP[i] %= MOD;
    }

    System.out.println(DP[N]);
  }
}