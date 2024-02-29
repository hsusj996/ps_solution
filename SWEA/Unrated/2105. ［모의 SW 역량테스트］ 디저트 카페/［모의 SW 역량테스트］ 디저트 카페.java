import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
  static StringBuilder result = new StringBuilder();
  static StringTokenizer st = null;
  static int T, N;
  static int[][] board;
  static int maxEat;
  static int[] dx = { 1, 1, -1, -1 };
  static int[] dy = { 1, -1, -1, 1 };

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());
    for (int test_case = 1; test_case <= T; test_case++) {
      result.append("#").append(test_case).append(" ");
      N = Integer.parseInt(br.readLine());
      board = new int[N][N];
      maxEat = -1;

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      for (int l = 1; l <= N - 2; l++) {
        for (int r = 1; r <= N - 2 && l + r <= N - 1; r++) {
          for (int y = l; y < N - r; y++) {
            for (int x = 0; x < N - l - r; x++) {
              int desert = getDesert(l, r, x, y);

              maxEat = Math.max(maxEat, desert);
            }
          }
        }
      }

      result.append(maxEat).append("\n");
    }

    System.out.println(result);
  }

  private static int getDesert(int l, int r, int x, int y) {
    Set<Integer> desertSet = new HashSet<>();
    int[][] Boundary = { { x, x + l + r }, { y - l, y + r } };

    int[] cur = { x, y };
    desertSet.add(board[x][y]);
    int direction = 0;

    while (true) {
      int nextX = cur[0] + dx[direction];
      int nextY = cur[1] + dy[direction];

      if (IsOutBound(nextX, nextY, Boundary)) {
        direction = (direction + 1) % 4;
        continue;
      }

      if (nextX == x && nextY == y) {
        break;
      }

      if (desertSet.contains(board[nextX][nextY])) {
        return -1;
      }

      desertSet.add(board[nextX][nextY]);
      cur[0] = nextX;
      cur[1] = nextY;
    }

    return desertSet.size();
  }

  private static boolean IsOutBound(int x, int y, int[][] boundary) {
    return x < boundary[0][0] || x > boundary[0][1] || y < boundary[1][0] || y > boundary[1][1];
  }
}