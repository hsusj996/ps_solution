package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob1890 {
  static int[][] gameBoard;
  static long[][] dp;
  static int N;
  static StringTokenizer st = null;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    gameBoard = new int[N][N];
    dp = new long[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        gameBoard[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    dp[0][0] = 1;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (i == N - 1 && j == N - 1) {
          break;
        }

        int delta = gameBoard[i][j];
        // right
        int[] newR = { i, j + delta };
        if (newR[0] >= 0 && newR[0] < N && newR[1] >= 0 && newR[1] < N) {
          dp[newR[0]][newR[1]] += dp[i][j];
        }
        // down
        int[] newD = { i + delta, j };
        if (newD[0] >= 0 && newD[0] < N && newD[1] >= 0 && newD[1] < N) {
          dp[newD[0]][newD[1]] += dp[i][j];
        }
      }
    }

    System.out.println(dp[N - 1][N - 1]);
  }

  private static void bfs() {
    Queue<int[]> q = new ArrayDeque<>();
    q.add(new int[] { 0, 0 });
    while (!q.isEmpty()) {
      int[] cur = q.poll();

      if (cur[0] == N - 1 && cur[1] == N - 1) {
        continue;
      }

      int delta = gameBoard[cur[0]][cur[1]];
      // right
      int[] newR = { cur[0], cur[1] + delta };
      if (newR[0] >= 0 && newR[0] < N && newR[1] >= 0 && newR[1] < N) {
        dp[newR[0]][newR[1]] += dp[cur[0]][cur[1]];
        q.add(newR);
      }
      // down
      int[] newD = { cur[0] + delta, cur[1] };
      if (newD[0] >= 0 && newD[0] < N && newD[1] >= 0 && newD[1] < N) {
        dp[newD[0]][newD[1]] += dp[cur[0]][cur[1]];
        q.add(newD);
      }
    }
  }

}
