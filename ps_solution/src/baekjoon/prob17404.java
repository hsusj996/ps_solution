package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob17404 {
  static final int RED = 0;
  static final int GREEN = 1;
  static final int BLUE = 2;
  static final int INF = 1_000_000_000;

  static StringTokenizer st = null;
  static int N;
  static int[][][] DP;
  static int[][] cost;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    DP = new int[3][3][N + 1];
    cost = new int[N + 1][3];

    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      cost[i][0] = Integer.parseInt(st.nextToken());
      cost[i][1] = Integer.parseInt(st.nextToken());
      cost[i][2] = Integer.parseInt(st.nextToken());
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (i == j) {
          DP[i][j][2] = INF;
          continue;
        }

        DP[i][j][2] = cost[1][i] + cost[2][j];
      }
    }
    for (int i = 3; i <= N; i++) {
      DP[0][0][i] = Math.min(DP[0][1][i - 1], DP[0][2][i - 1]) + cost[i][0];
      DP[0][1][i] = Math.min(DP[0][0][i - 1], DP[0][2][i - 1]) + cost[i][1];
      DP[0][2][i] = Math.min(DP[0][0][i - 1], DP[0][1][i - 1]) + cost[i][2];
      DP[1][0][i] = Math.min(DP[1][1][i - 1], DP[1][2][i - 1]) + cost[i][0];
      DP[1][1][i] = Math.min(DP[1][0][i - 1], DP[1][2][i - 1]) + cost[i][1];
      DP[1][2][i] = Math.min(DP[1][0][i - 1], DP[1][1][i - 1]) + cost[i][2];
      DP[2][0][i] = Math.min(DP[2][1][i - 1], DP[2][2][i - 1]) + cost[i][0];
      DP[2][1][i] = Math.min(DP[2][0][i - 1], DP[2][2][i - 1]) + cost[i][1];
      DP[2][2][i] = Math.min(DP[2][0][i - 1], DP[2][1][i - 1]) + cost[i][2];
    }

    int min = INF;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (i == j)
          continue;

        min = Math.min(min, DP[i][j][N]);
      }
    }
    // for(int i=0;i<3;i++){
    // for(int j=0;j<3;j++){
    // if(i == j){
    // continue;
    // }

    // int tmp = INF;
    // for(int k=0;k<3;k++){
    // tmp = Math.min(tmp, DP[i][k][N-1]);
    // }
    // DP[i][j][N] = tmp + cost[j][N];
    // }
    // }

    System.out.println(min);
  }
}
