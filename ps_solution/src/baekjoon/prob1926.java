package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob1926 {
  static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };
  static int n, m;
  static int cnt = 0;
  static int max = 0;
  static int[][] board;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    board = new int[n][m];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i][j] == 1) {
          bfs(i, j);
          cnt++;
        }
      }
    }

    System.out.println(cnt + "\n" + max);
  }

  private static void bfs(int row, int col) {
    int areaTmp = 0;
    Queue<int[]> q = new ArrayDeque<>();
    board[row][col] = 0;
    areaTmp++;
    q.add(new int[] { row, col });

    while (!q.isEmpty()) {
      int[] cur = q.poll();

      for (int i = 0; i < 4; i++) {
        int nr = cur[0] + d[0][i];
        int nc = cur[1] + d[1][i];

        if (IsOutBound(nr, nc) || board[nr][nc] == 0) {
          continue;
        }

        areaTmp++;
        board[nr][nc] = 0;
        q.add(new int[] { nr, nc });
      }
    }

    max = Math.max(max, areaTmp);
  }

  private static boolean IsOutBound(int r, int c) {
    return r < 0 || r >= n || c < 0 || c >= m;
  }
}