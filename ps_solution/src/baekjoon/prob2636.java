package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob2636 {
  static class cdnt {
    int row;
    int col;

    public cdnt(int row, int col) {
      super();
      this.row = row;
      this.col = col;
    }
  }

  static int[][] map;
  static List<cdnt> cheese = new ArrayList<>();
  static int[] d_row = { -1, 0, 1, 0 };
  static int[] d_col = { 0, 1, 0, -1 };
  static int N, M;
  static int cnt;
  static int hour = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (map[i][j] == 1) {
          cheese.add(new cdnt(i, j));
        }
      }
    }

    cnt = cheese.size();
    while (true) {
      hour++;
      setOutAir();
      CheeseMelting();
      if (cheese.size() == 0) {
        break;
      }
      cnt = cheese.size();
    }

    System.out.println(hour + "\n" + cnt);
  }

  private static void setOutAir() {
    boolean[][] visited = new boolean[N][M];
    Queue<cdnt> q = new LinkedList<>();
    q.add(new cdnt(0, 0));
    visited[0][0] = true;
    map[0][0] = 2;

    while (!q.isEmpty()) {
      cdnt cur = q.poll();

      for (int i = 0; i < 4; i++) {
        int newRow = cur.row + d_row[i];
        int newCol = cur.col + d_col[i];

        if (IsOutBound(newRow, newCol) || visited[newRow][newCol]) {
          continue;
        }

        if (map[newRow][newCol] == 1) {
          continue;
        }

        q.add(new cdnt(newRow, newCol));
        visited[newRow][newCol] = true;
        map[newRow][newCol] = 2;
      }
    }
  }

  private static void CheeseMelting() {
    for (int i = cheese.size() - 1; i >= 0; i--) {
      cdnt c = cheese.get(i);
      for (int j = 0; j < 4; j++) {
        int newRow = c.row + d_row[j];
        int newCol = c.col + d_col[j];

        if (IsOutBound(newRow, newCol)) {
          continue;
        }

        if (map[newRow][newCol] == 2) {
          map[c.row][c.col] = 0;
          cheese.remove(i);
          break;
        }
      }
    }

  }

  private static boolean IsOutBound(int row, int col) {
    return row < 0 || row >= N || col < 0 || col >= M;
  }
}
