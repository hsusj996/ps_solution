package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class prob1938 {
  static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

  static class Train {
    int[] center;
    int state;
    int depth;

    public Train(int state) {
      super();
      this.center = new int[2];
      this.state = state;
    }

    public Train(int[] center, int state) {
      super();
      this.center = center;
      this.state = state;
    }

    public Train(int[] center, int state, int depth) {
      super();
      this.center = center;
      this.state = state;
      this.depth = depth;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Train)) {
        return false;
      }

      Train o = (Train) obj;

      if (this.center[0] == o.center[0] && this.center[1] == o.center[1] && this.center == o.center) {
        return true;
      }

      return false;
    }

  }

  static char[][] board;
  static boolean[][][] visited;
  static int N;
  static Train firstTrain;
  static Train dest;
  static int ans = Integer.MAX_VALUE;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    board = new char[N][N];
    for (int i = 0; i < N; i++) {
      String input = br.readLine();
      for (int j = 0; j < N; j++) {
        board[i][j] = input.charAt(j);
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (board[i][j] == 'B') {
          int nx = i + d[0][1];
          int ny = j + d[1][1];
          if (!IsOutBound(nx, ny) && board[nx][ny] == 'B') {
            firstTrain = new Train(new int[] { nx, ny }, 0);
            break;
          }
          nx = i + d[0][2];
          ny = j + d[1][2];
          if (!IsOutBound(nx, ny) && board[nx][ny] == 'B') {
            firstTrain = new Train(new int[] { nx, ny }, 1);
            break;
          }
        }
      }
      if (firstTrain != null) {
        break;
      }
    }
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (board[i][j] == 'E') {
          int nx = i + d[0][1];
          int ny = j + d[1][1];
          if (!IsOutBound(nx, ny) && board[nx][ny] == 'E') {
            dest = new Train(new int[] { nx, ny }, 0);
            break;
          }
          nx = i + d[0][2];
          ny = j + d[1][2];
          if (!IsOutBound(nx, ny) && board[nx][ny] == 'E') {
            dest = new Train(new int[] { nx, ny }, 1);
            break;
          }
        }
      }
      if (dest != null) {
        break;
      }
    }

    visited = new boolean[N][N][2];
    visited[firstTrain.center[0]][firstTrain.center[1]][firstTrain.state] = true;
    bfs();

    System.out.println(ans);
    return;
  }

  private static void bfs() {
    visited = new boolean[N][N][2];
    visited[firstTrain.center[0]][firstTrain.center[1]][firstTrain.state] = true;
    Queue<Train> q = new ArrayDeque<>();

    q.add(firstTrain);

    while (!q.isEmpty()) {
      Train curTrain = q.poll();
      if (curTrain.equals(dest)) {
        ans = curTrain.depth;
      }

      for (int i = 0; i < 4; i++) {
        int nx = curTrain.center[0] + d[0][i];
        int ny = curTrain.center[1] + d[1][i];
        int nx2 = nx + d[0][i];
        int ny2 = ny + d[1][i];

        if (IsOutBound(nx2, ny2) || visited[nx][ny][curTrain.state]) {
          continue;
        }

        if (board[nx2][ny2] == '1') {
          continue;
        }

        visited[nx][ny][curTrain.state] = true;
        q.add(new Train(new int[] { nx, ny }, curTrain.state, curTrain.depth + 1));
      }

      // 회전
      Train nTrain = null;
      if (curTrain.state == 0) {
        nTrain = new Train(curTrain.center, 1, curTrain.depth + 1);
      } else {
        nTrain = new Train(curTrain.center, 0, curTrain.depth + 1);
      }

      if (!visited[nTrain.center[0]][nTrain.center[1]][nTrain.state]) {
        boolean flag = false;
        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            int cx = nTrain.center[0] - 1 + i;
            int cy = nTrain.center[1] - 1 + j;

            if (IsOutBound(cx, cy) || board[cx][cy] == '1') {
              flag = true;
              break;
            }
          }
          if (flag) {
            break;
          }
        }
        if (!flag) {
          visited[nTrain.center[0]][nTrain.center[1]][nTrain.state] = true;
          q.add(nTrain);
        }
      }
    }
  }

  private static boolean IsOutBound(int x, int y) {
    return x < 0 || x >= N || y < 0 || y >= N;
  }

}
