package baekjoon;

import java.util.*;
import java.io.*;

public class prob2638 {
  static int N;
  static int M;
  static int[][] cheese;
  static int sec;
  static int[] d_row = { -1, 0, 1, 0 };
  static int[] d_col = { 0, 1, 0, -1 };
  static boolean[][] visited;

  static class xy {
    int x;
    int y;

    public xy(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    cheese = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      for (int j = 0; j < M; j++) {
        cheese[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    visited = new boolean[N][M];
    for (int i = 2; i < N - 2; i++) {
      for (int j = 2; j < M - 2; j++) {
        if (cheese[i][j] == 0 && !visited[i][j]) {
          CheckInnerSpaces(i, j);
        }
      }
    }

    while (true) {
      boolean flag = false;
      visited = new boolean[N][M];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (cheese[i][j] == 1 && !visited[i][j]) {
            flag = true;

            CheeseUpdate(i, j);
          }
        }
      }

      if (flag) {
        sec++;
      } else {
        break;
      }
    }

    System.out.println(sec);
  }

  static void CheeseUpdate(int row, int col) {
    ArrayList<xy> RemoveCheeses = new ArrayList<>();
    Queue<xy> q = new LinkedList<>();
    q.add(new xy(row, col));
    visited[row][col] = true;

    while (!q.isEmpty()) {
      xy now = q.poll();
      int OuterCnt = 0;

      for (int i = 0; i < 4; i++) {
        int new_x = now.x + d_row[i];
        int new_y = now.y + d_col[i];

        if (new_x < 0 || new_x >= N || new_y < 0 || new_y >= M) {
          continue;
        }

        if (visited[new_x][new_y]) {
          continue;
        }

        if (cheese[new_x][new_y] == 0) {
          OuterCnt++;
          continue;
        }

        if (cheese[new_x][new_y] == 1) {
          q.add(new xy(new_x, new_y));
          visited[new_x][new_y] = true;
        }
      }

      if (OuterCnt >= 2) {
        RemoveCheeses.add(new xy(now.x, now.y));
      }
    }

    for (xy cdnt : RemoveCheeses) {
      cheese[cdnt.x][cdnt.y] = 0;
    }
  }

  static void CheckInnerSpaces(int row, int col) {
    ArrayList<xy> InnerSpaces = new ArrayList<>();
    Queue<xy> q = new LinkedList<>();
    q.add(new xy(row, col));
    InnerSpaces.add(new xy(row, col));
    visited[row][col] = true;

    while (!q.isEmpty()) {
      xy now = q.poll();

      for (int i = 0; i < 4; i++) {
        int new_x = now.x + d_row[i];
        int new_y = now.y + d_col[i];

        if (new_x < 2 || new_x >= N - 2 || new_y < 2 || new_y >= M - 2) {
          return;
        }

        if (cheese[new_x][new_y] == 1) {
          continue;
        }
        if (visited[new_x][new_y]) {
          continue;
        }

        q.add(new xy(new_x, new_y));
        InnerSpaces.add(new xy(new_x, new_y));
        visited[new_x][new_y] = true;
      }
    }

    for (xy cdnt : InnerSpaces) {
      cheese[cdnt.x][cdnt.y] = 2;
    }
  }
}