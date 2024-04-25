package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob14868 {
  static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

  static StringTokenizer st = null;
  static int[][] board;
  static int[] parents;
  static Queue<int[]> q = new ArrayDeque<>();
  static int N, K;
  static HashSet<Integer> civilSet = new HashSet<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    board = new int[N + 1][N + 1];
    parents = new int[K + 1];
    MakeSet();

    for (int i = 1; i <= K; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());

      q.add(new int[] { x, y });
      civilSet.add(i);
      board[x][y] = i;
    }

    int ans = bfs();
    System.out.println(ans);
  }

  private static int bfs() {
    int depth = 0;
    int cnt = 1;
    while (true) {
      int size = q.size();

      while (size-- > 0) {
        int[] cur = q.poll();

        for (int i = 0; i < 4; i++) {
          int nx = cur[0] + d[0][i];
          int ny = cur[1] + d[1][i];

          if (IsOutBound(nx, ny) || board[cur[0]][cur[1]] == board[nx][ny]) {
            continue;
          }

          if (board[nx][ny] != 0) {
            if (Union(board[cur[0]][cur[1]], board[nx][ny])) {
              cnt++;
            }

            continue;
          }
        }
        q.add(cur);
      }

      if (cnt == K) {
        break;
      }
      depth++;

      size = q.size();
      while (size-- > 0) {
        int[] cur = q.poll();

        for (int i = 0; i < 4; i++) {
          int nx = cur[0] + d[0][i];
          int ny = cur[1] + d[1][i];

          if (IsOutBound(nx, ny) || board[cur[0]][cur[1]] == board[nx][ny]) {
            continue;
          }

          if (board[nx][ny] != 0) {
            if (Union(board[cur[0]][cur[1]], board[nx][ny])) {
              cnt++;
            }

            continue;
          }

          board[nx][ny] = board[cur[0]][cur[1]];
          q.add(new int[] { nx, ny });
        }
      }

      if (cnt == K) {
        break;
      }
    }

    return depth;
  }

  private static void PrintForDebug(int depth) {
    System.out.println("---------" + depth + "------------");
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }

  private static void MakeSet() {
    for (int i = 1; i <= K; i++) {
      parents[i] = i;
    }
  }

  private static int FindSet(int a) {
    if (parents[a] == a) {
      return a;
    }

    return parents[a] = FindSet(parents[a]);
  }

  private static boolean Union(int a, int b) {
    int aRoot = FindSet(a);
    int bRoot = FindSet(b);

    if (aRoot == bRoot) {
      return false;
    }
    parents[bRoot] = aRoot;
    return true;
  }

  private static boolean IsOutBound(int x, int y) {
    return x <= 0 || x > N || y <= 0 || y > N;
  }
}
