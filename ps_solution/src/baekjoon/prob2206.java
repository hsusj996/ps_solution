package baekjoon;

import java.util.*;
import java.io.*;

public class prob2206 {
  static int N;
  static int M;
  static int[][] board;
  static boolean[][][] visited;
  static int[] d_row = { -1, 0, 1, 0 };
  static int[] d_col = { 0, 1, 0, -1 };
  static int min = Integer.MAX_VALUE;

  static class xy {
    int x;
    int y;
    int cnt;
    boolean crash_wall;

    public xy(int x, int y, int cnt, boolean crash_wall) {
      this.x = x;
      this.y = y;
      this.cnt = cnt;
      this.crash_wall = crash_wall;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    board = new int[N][M];
    visited = new boolean[2][N][M];

    for (int i = 0; i < N; i++) {
      String s = br.readLine();
      for (int j = 0; j < M; j++) {
        board[i][j] = s.charAt(j) - '0';
      }
    }

    bfs();

    if (min == Integer.MAX_VALUE) {
      System.out.println(-1);
    } else
      System.out.println(min);
  }

  static void bfs() {
    Queue<xy> q = new LinkedList<>();
    q.add(new xy(0, 0, 1, false));
    visited[0][0][0] = true;

    while (!q.isEmpty()) {
      xy now = q.poll();

      if (now.x == N - 1 && now.y == M - 1) {
        min = now.cnt;
        return;
      }

      for (int i = 0; i < 4; i++) {
        int new_x = now.x + d_row[i];
        int new_y = now.y + d_col[i];
        if (new_x < 0 || new_x >= N || new_y < 0 || new_y >= M) {
          continue;
        }

        if (now.crash_wall) {
          if (board[new_x][new_y] == 0 && !visited[1][new_x][new_y]) {
            visited[1][new_x][new_y] = true;
            q.add(new xy(new_x, new_y, now.cnt + 1, true));
          }
        } else {
          if (board[new_x][new_y] == 1) {
            visited[1][new_x][new_y] = true;
            q.add(new xy(new_x, new_y, now.cnt + 1, true));
          } else if (!visited[0][new_x][new_y]) {
            visited[0][new_x][new_y] = true;
            q.add(new xy(new_x, new_y, now.cnt + 1, false));
          }
        }
      }
    }
  }
}
