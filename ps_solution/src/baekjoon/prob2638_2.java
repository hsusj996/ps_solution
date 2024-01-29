package baekjoon;

import java.util.*;
import java.io.*;

public class prob2638_2 {
  static int N;
  static int M;
  static int[][] cheese;
  static int sec;
  static int[] d_row = { -1, 0, 1, 0 };
  static int[] d_col = { 0, 1, 0, -1 };
  static boolean[][] visited;
  static ArrayList<xy> arr_cheese;
  static int cheese_cnt = 0;

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
    arr_cheese = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      for (int j = 0; j < M; j++) {
        cheese[i][j] = Integer.parseInt(st.nextToken());
        if (cheese[i][j] == 1) {
          arr_cheese.add(new xy(i, j));
          cheese_cnt++;
        }
      }
    }

    while (cheese_cnt != 0) {
      sec++;
      visited = new boolean[N][M];
      SetOuterAir(new xy(0, 0));
      CheeseMelting();
    }

    System.out.println(sec);
  }

  static void CheeseMelting() {
    for (int i = 0; i < arr_cheese.size(); i++) {
      xy now = arr_cheese.get(i);
      int cnt = 0;

      for (int j = 0; j < 4; j++) {
        int new_x = now.x + d_row[j];
        int new_y = now.y + d_col[j];

        if (cheese[new_x][new_y] == 2) {
          cnt++;
        }
      }

      if (cnt >= 2) {
        cheese[now.x][now.y] = 0;
        cheese_cnt--;
        arr_cheese.remove(i--);
      }
    }
  }

  static void SetOuterAir(xy cdnt) {
    Queue<xy> q = new LinkedList<>();
    q.add(cdnt);
    cheese[cdnt.x][cdnt.y] = 2;
    visited[cdnt.x][cdnt.y] = true;

    while (!q.isEmpty()) {
      xy now = q.poll();

      for (int i = 0; i < 4; i++) {
        int new_x = now.x + d_row[i];
        int new_y = now.y + d_col[i];

        if (new_x < 0 || new_x >= N || new_y < 0 || new_y >= M) {
          continue;
        }

        if (visited[new_x][new_y]) {
          continue;
        }

        if (cheese[new_x][new_y] == 1) {
          continue;
        }
        q.add(new xy(new_x, new_y));
        cheese[new_x][new_y] = 2;
        visited[new_x][new_y] = true;
      }
    }
  }
}