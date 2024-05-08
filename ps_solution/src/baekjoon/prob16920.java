package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob16920 {
  static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

  static class xy {
    int x;
    int y;

    public xy(int x, int y) {
      this.x = x;
      this.y = y;
    }

  }

  static StringTokenizer st = null;
  static StringBuilder sb = new StringBuilder();
  static int N, M, P;
  static int[] sArr;
  static int[] scoreArr;
  static Queue<xy>[] qArr;
  static int EmptyCnt;
  static char[][] board;
  static boolean[][] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    P = Integer.parseInt(st.nextToken());

    EmptyCnt = N * M;
    board = new char[N][M];
    visited = new boolean[N][M];
    sArr = new int[P + 1];
    scoreArr = new int[P + 1];
    qArr = new ArrayDeque[P + 1];
    for (int i = 1; i <= P; i++) {
      qArr[i] = new ArrayDeque<>();
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= P; i++) {
      sArr[i] = Math.min(Integer.parseInt(st.nextToken()), N + M);
    }

    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < M; j++) {
        board[i][j] = str.charAt(j);
        if (board[i][j] != '.') {
          EmptyCnt--;
          if (board[i][j] != '#') {
            qArr[board[i][j] - '0'].add(new xy(i, j));
            scoreArr[board[i][j] - '0']++;
          }
        }
      }
    }

    bfs();

    resultCount();

    System.out.println(sb.toString());
  }

  private static void resultCount() {
    for (int i = 1; i <= P; i++) {
      sb.append(scoreArr[i]).append(" ");
    }

    return;
  }

  private static void bfs() {
    while (true) {

      boolean flag = false;
      for (int i = 1; i <= P; i++) {
        if (qArr[i].size() > 0) {
          flag = true;
          break;
        }
      }

      if (!flag) {
        return;
      }

      for (int i = 1; i <= P; i++) {
        // PrintForDebug(); // 디버깅용 출력

        for (int depth = 0; depth < sArr[i]; depth++) {
          if (EmptyCnt == 0) {
            return;
          }

          int qSize = qArr[i].size();

          while (qSize-- > 0) {
            xy cur = qArr[i].poll();

            if (visited[cur.x][cur.y]) {
              continue;
            }
            visited[cur.x][cur.y] = true;

            for (int k = 0; k < 4; k++) {
              int nx = cur.x + d[0][k];
              int ny = cur.y + d[1][k];

              if (IsOutBound(nx, ny) || board[nx][ny] != '.') {
                continue;
              }

              EmptyCnt--;
              board[nx][ny] = (char) (i + '0');
              scoreArr[i]++;
              qArr[i].add(new xy(nx, ny));
            }
          }
        }
      }
    }
  }

  private static boolean IsOutBound(int x, int y) {
    return x < 0 || x >= N || y < 0 || y >= M;
  }

  private static void PrintForDebug() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("EmptyCnt = " + EmptyCnt);
    System.out.println("========================================");
    System.out.println();
  }
}
