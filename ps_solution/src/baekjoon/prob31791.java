package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob31791 {
  static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

  static class xy {
    int x;
    int y;
    int time;

    public xy(int x, int y) {
      this.x = x;
      this.y = y;
      this.time = 0;
    }

  }

  static StringTokenizer st = null;
  static StringBuilder sb = new StringBuilder();
  static int N, M;
  static int Tg, Tb, X, B;
  static Queue<xy> q = new ArrayDeque<>();
  static Queue<xy> buildingQ = new ArrayDeque<>();
  static char[][] board;
  static boolean[][] danger;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    Tg = Integer.parseInt(st.nextToken());
    Tb = Integer.parseInt(st.nextToken());
    X = Integer.parseInt(st.nextToken());
    B = Integer.parseInt(st.nextToken());

    board = new char[N + 1][M + 1];
    danger = new boolean[N + 1][M + 1];
    for (int i = 1; i <= N; i++) {
      String input = br.readLine();
      for (int j = 1; j <= M; j++) {
        board[i][j] = input.charAt(j - 1);
        if (board[i][j] == '*') {
          q.add(new xy(i, j));
          danger[i][j] = true;
        }
      }
    }

    bfs();

    boolean flag = false;
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        if (board[i][j] != '*') {
          flag = true;
          sb.append(i).append(" ").append(j).append("\n");
        }
      }
    }

    System.out.println(flag ? sb.toString() : -1);
  }

  private static void bfs() {
    for (int t = 0; t <= Tg; t++) {
      // PrintForDebug();
      while (!buildingQ.isEmpty() && buildingQ.peek().time == t) {
        q.add(buildingQ.poll());
      }
      int qSize = q.size();

      while (qSize-- > 0) {
        xy cur = q.poll();

        board[cur.x][cur.y] = '*';

        for (int i = 0; i < 4; i++) {
          int nx = cur.x + d[0][i];
          int ny = cur.y + d[1][i];

          if (IsOutBound(nx, ny) || danger[nx][ny]) {
            continue;
          }

          xy next = new xy(nx, ny);
          danger[nx][ny] = true;
          if (board[nx][ny] == '#') {
            next.time = t + Tb + 1;
            buildingQ.add(next);
          } else {
            q.add(next);
          }
        }
      }
    }
  }

  private static void PrintForDebug() {
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  private static boolean IsOutBound(int nx, int ny) {
    return nx <= 0 || nx > N || ny <= 0 || ny > M;
  }
}
