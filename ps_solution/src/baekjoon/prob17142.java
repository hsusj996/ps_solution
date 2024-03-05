package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob17142 {
  static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };
  static final int INF = 1_000_000;

  static class Virus {
    int x;
    int y;
    int depth;

    public Virus(int x, int y, int depth) {
      this.x = x;
      this.y = y;
      this.depth = depth;
    }

  }

  static StringTokenizer st = null;
  static int N, M;
  static int[][] board;
  static int emptyCnt;
  static int[] selectedVirus;
  static List<Virus> virusList;
  static int ans;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    board = new int[N][N];

    emptyCnt = 0;
    virusList = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
        if (board[i][j] == 0) {
          emptyCnt++;
        }
        if (board[i][j] == 2) {
          Virus v = new Virus(i, j, 0);
          virusList.add(v);
        }
      }
    }

    ans = INF;
    selectedVirus = new int[M];

    if(emptyCnt == 0){
      System.out.println(0);
      return;
    }

    CombiInitVirus(0, 0);
    if (ans == INF) {
      ans = -1;
    }

    System.out.println(ans);
  }

  private static void CombiInitVirus(int prev, int depth) {
    if (depth == M) {
      Simulation();
      return;
    }

    for (int i = prev; i < virusList.size(); i++) {
      selectedVirus[depth] = i;
      CombiInitVirus(i + 1, depth + 1);
    }
  }

  private static void Simulation() {
    int tmp = emptyCnt;

    boolean[][] visited = new boolean[N][N];
    Queue<Virus> q = new ArrayDeque<>();

    
    for (int i = 0; i < M; i++) {
      Virus c = virusList.get(selectedVirus[i]);
      q.add(c);
      visited[c.x][c.y] = true;
    }

    while (!q.isEmpty()) {
      Virus cur = q.poll();

      for (int i = 0; i < 4; i++) {
        int newX = cur.x + d[0][i];
        int newY = cur.y + d[1][i];

        if (IsOutBound(newX, newY) || visited[newX][newY] || board[newX][newY] == 1) {
          continue;
        }

        if (board[newX][newY] == 2) {
          q.add(new Virus(newX, newY, cur.depth + 1));
          visited[newX][newY] = true;
        }

        if (board[newX][newY] == 0) {
          q.add(new Virus(newX, newY, cur.depth + 1));
          visited[newX][newY] = true;
          tmp--;

          if (tmp == 0) {
            ans = Math.min(ans, cur.depth + 1);
            return;
          }
        }
      }
    }
  }

  private static boolean IsOutBound(int x, int y) {
    return x < 0 || x >= N || y < 0 || y >= N;
  }
}
