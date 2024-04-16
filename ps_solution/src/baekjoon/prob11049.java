package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob11049 {
  static StringTokenizer st = null;
  static int[][] matArr = new int[501][501];
  static int N;
  static int ans = Integer.MAX_VALUE;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      matArr[r][c]++;
    }

    for (int i = 1; i <= 500; i++) {
      for (int j = 1; j <= 500; j++) {
        if (matArr[i][j] > 0) {
          matArr[i][j]--;
          dfs(0, 0, i, j);
          matArr[i][j]++;
        }
      }
    }

    System.out.println(ans);
  }

  private static void dfs(int depth, int v, int r, int c) {
    if (depth == N - 1) {
      ans = Math.min(ans, v);
      return;
    }

    for (int i = 1; i <= 500; i++) {
      if (matArr[c][i] > 0) {
        int newV = r * c * i + v;
        int newR = r;
        int newC = i;
        matArr[c][i]--;
        dfs(depth + 1, newV, newR, newC);
        matArr[c][i]++;
      }
    }
  }
}
