package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob10942 {
  static StringTokenizer st = null;
  static StringBuilder sb = new StringBuilder();
  static int N;
  static int[] arr;
  static int M;
  static int[][] dp;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    dp = new int[N][N];
    for (int i = 0; i < N; i++) {
      dp[i][i] = 1;
    }
    for (int i = 0; i < N - 1; i++) {
      if (arr[i] == arr[i + 1]) {
        dp[i][i + 1] = 1;
      }
    }

    for (int k = 2; k < N; k++) {
      for (int i = 0; i < N - k; i++) {
        int j = i + k;
        if (arr[i] == arr[j] && dp[i + 1][j - 1] == 1) {
          dp[i][j] = 1;
        }
      }
    }

    M = Integer.parseInt(br.readLine());
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());

      sb.append(dp[s - 1][e - 1]).append("\n");
    }

    System.out.println(sb.toString());
  }
}
