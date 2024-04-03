package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob2458 {
  static final int INF = 1000000;
  static StringTokenizer st = null;
  static StringBuilder sb = new StringBuilder();
  static int N, M;
  static int[][] graph;
  static int[][] floyd;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    graph = new int[N + 1][N + 1];
    floyd = new int[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      Arrays.fill(floyd[i], INF);
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      graph[a][b] = 1;
      floyd[a][b] = 1;
    }

    for (int i = 1; i <= N; i++) {
      floyd[i][i] = 0;
    }

    for (int k = 1; k <= N; k++) {
      for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
          floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
        }
      }
    }

    int cnt = 0;

    for (int i = 1; i <= N; i++) {
      boolean flag = true;
      for (int j = 1; j <= N; j++) {
        if (i == j)
          continue;

        if (floyd[i][j] >= INF && floyd[j][i] >= INF) {
          flag = false;
          break;
        }
      }

      if (flag)
        cnt++;
    }

    System.out.println(cnt);
  }

}
