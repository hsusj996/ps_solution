import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
  static class cdnt {
    int row;
    int col;
    int depth;

    public cdnt(int row, int col, int depth) {
      this.row = row;
      this.col = col;
      this.depth = depth;
    }

  }

  static StringBuilder result = new StringBuilder();
  static StringTokenizer st = null;
  static int[] d_row = { -1, 0, 1, 0 };
  static int[] d_col = { 0, 1, 0, -1 };
  static List<cdnt> startList;
  static int[][] map;
  static int T, N, K;
  static int maxLength;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    T = Integer.parseInt(br.readLine());
    for (int test_case = 1; test_case <= T; test_case++) {
      result.append("#").append(test_case).append(" ");

      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());

      // 초기화
      map = new int[N][N];
      maxLength = 0;

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }



      // 시작점 리스트 생성
      int maxHeight = 0;
      startList = new ArrayList<>();
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (maxHeight == map[i][j]) {
            startList.add(new cdnt(i, j, 1));
          } else if (maxHeight < map[i][j]) {
            maxHeight = map[i][j];
            startList = new ArrayList<>();
            startList.add(new cdnt(i, j, 1));
          }
        }
      }

      // 공사하지 않을 때 BFS
      BFS();

      // 공사할 때
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          for (int k = 1; k <= K; k++) {
            if (map[i][j] >= k) {
              map[i][j] -= k;
              BFS();
              map[i][j] += k;
            }
          }
        }
      }

      result.append(maxLength).append("\n");
    }

    System.out.println(result);
  }

  private static void BFS() {
    // 시작점마다 BFS 수행
    for (cdnt start : startList) {
      int row = start.row;
      int col = start.col;

      int maxDepth = 0;
      Queue<cdnt> q = new ArrayDeque<>();
      q.add(new cdnt(row, col, 1));

      while (!q.isEmpty()) {
        cdnt cur = q.poll();
        maxDepth = cur.depth;

        for (int i = 0; i < 4; i++) {
          int newRow = cur.row + d_row[i];
          int newCol = cur.col + d_col[i];

          if (IsOutBound(newRow, newCol)) {
            continue;
          }

          if (map[newRow][newCol] < map[cur.row][cur.col]) {
            q.add(new cdnt(newRow, newCol, cur.depth + 1));
          }
        }
      }
      maxLength = Math.max(maxLength, maxDepth);
    }
  }

  private static boolean IsOutBound(int row, int col) {
    return row < 0 || row >= N || col < 0 || col >= N;
  }
}