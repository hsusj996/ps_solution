package baekjoon;

import java.util.*;
import java.io.*;

public class prob2096_2 {
  static int N;
  static int[] d_col = { -1, 0, 1 };
  static int[] board;
  static int[] max_dp;
  static int[] min_dp;
  static int max = Integer.MIN_VALUE;
  static int min = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    board = new int[3];
    max_dp = new int[3];
    min_dp = new int[3];

    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int v0 = Integer.parseInt(st.nextToken());
      int v1 = Integer.parseInt(st.nextToken());
      int v2 = Integer.parseInt(st.nextToken());

      if (i == 0) {
        max_dp[0] = min_dp[0] = v0;
        max_dp[1] = min_dp[1] = v1;
        max_dp[2] = min_dp[2] = v2;
      } else {
        int buf_max_dp_0 = max_dp[0];
        int buf_max_dp_2 = max_dp[2];
        max_dp[0] = Math.max(max_dp[0], max_dp[1]) + v0;
        max_dp[2] = Math.max(max_dp[1], max_dp[2]) + v2;
        max_dp[1] = Math.max(Math.max(buf_max_dp_0, max_dp[1]), buf_max_dp_2) + v1;

        int buf_min_dp_0 = min_dp[0];
        int buf_min_dp_2 = min_dp[2];
        min_dp[0] = Math.min(min_dp[0], min_dp[1]) + v0;
        min_dp[2] = Math.min(min_dp[1], min_dp[2]) + v2;
        min_dp[1] = Math.min(Math.min(buf_min_dp_0, min_dp[1]), buf_min_dp_2) + v1;
      }
    }

    max = Math.max(Math.max(max_dp[0], max_dp[1]), max_dp[2]);
    min = Math.min(Math.min(min_dp[0], min_dp[1]), min_dp[2]);

    System.out.println(max + " " + min);
  }
}
