package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob14391 {
  static StringTokenizer st = null;
  static int N, M;
  static int[][] board;
  static boolean[][] visited;
  static int max;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    board = new int[N][M];
    visited = new boolean[N][M];

    for (int i = 0; i < N; i++) {
      String s = br.readLine();
      for (int j = 0; j < M; j++) {
        board[i][j] = s.charAt(j) - '0';
      }
    }

    BruteForce(0, 0, 0);

    System.out.println(max);
  }

  private static void BruteForce(int x, int y, int sum) {
    // 가로로 자르기
    for (int i = 0; i < M - y; i++) {
      StringBuilder tmp = new StringBuilder();
      for (int j = 0; j <= i; j++) {
        tmp.append(board[x][y + j]);
        visited[x][y + j] = true;
      }
      if (y + i >= M - 1) {
        BruteForce(x + 1, 0, sum + Integer.parseInt(tmp.toString()));
      } else {
        BruteForce(x, y + i, sum + Integer.parseInt(tmp.toString()));
      }

      for (int j = 0; j <= i; j++) {
        visited[x][y + j] = false;
      }
    }
  }
}
