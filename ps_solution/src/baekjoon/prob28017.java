package baekjoon;

import java.util.*;
import java.io.*;

public class prob28017 {
  static int N;
  static int M;
  static int[][] game;
  static int[][] dp;
  static int ans = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    game = new int[N][M];
    dp = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        game[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    for (int i = 0; i < M; i++) {
      dp[0][i] = game[0][i];
    }

    for (int i = 1; i < N; i++) {
      for (int j = 0; j < M; j++) {
        dp[i][j] = MinOfArr(dp[i - 1], j) + game[i][j];
      }
    }

    for (int i = 0; i < M; i++) {
      ans = Math.min(ans, dp[N - 1][i]);
    }

    System.out.println(ans);
  }

  static int MinOfArr(int[] arr, int col) {
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < arr.length; i++) {
      if (i == col) {
        continue;
      }
      min = Math.min(min, arr[i]);
    }

    return min;
  }
}
