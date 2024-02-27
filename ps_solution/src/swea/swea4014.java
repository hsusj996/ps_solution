package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea4014 {
  static StringBuilder result = new StringBuilder();
  static StringTokenizer st = null;
  static int T, N, X, cnt;
  static int[][] board;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    T = Integer.parseInt(br.readLine());
    for (int test_case = 1; test_case <= T; test_case++) {
      result.append("#").append(test_case).append(" ");
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      X = Integer.parseInt(st.nextToken());
      cnt = 0;

      board = new int[N][N];
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      CheckRow();
      CheckCol();

      result.append(cnt).append("\n");
    }

    System.out.println(result);
  }

  private static void CheckCol() {
    boolean[][] airway = new boolean[N][N];
    // 열 검사
    for (int i = 0; i < N; i++) {
      boolean flag = true;
      int prev = board[0][i];

      for (int j = 1; j < N; j++) {
        if (!flag) {
          break;
        }

        if (prev == board[j][i]) {
          continue;
        }

        if (Math.abs(prev - board[j][i]) >= 2) {
          flag = false;
          break;
        }

        if (prev < board[j][i]) {
          airway[j - 1][i] = true;
          for (int k = j - X; k < j - 1; k++) {
            if (k < 0 || board[k][i] != board[j - 1][i] || airway[k][i]) {
              flag = false;
              break;
            }
            airway[k][i] = true;
          }
        }

        if (prev > board[j][i]) {
          airway[j][i] = true;
          for (int k = j + X - 1; k > j; k--) {
            if (k >= N || board[k][i] != board[j][i] || airway[k][i]) {
              flag = false;
              break;
            }
            airway[k][i] = true;
          }
        }
        prev = board[j][i];
      }
      if (flag) {
        cnt++;
      }
    }
  }

  private static void CheckRow() {
    boolean[][] airway = new boolean[N][N];
    // 행 검사
    for (int i = 0; i < N; i++) {
      boolean flag = true;
      int prev = board[i][0];
      for (int j = 1; j < N; j++) {
        if (!flag) {
          break;
        }
        if (prev == board[i][j]) {
          continue;
        }

        if (Math.abs(prev - board[i][j]) >= 2) {
          flag = false;
          break;
        }

        if (prev < board[i][j]) {
          airway[i][j - 1] = true;
          for (int k = j - X; k < j - 1; k++) {
            if (k < 0 || board[i][k] != board[i][j - 1] || airway[i][k]) {
              flag = false;
              break;
            }
            airway[i][k] = true;
          }
        }

        if (prev > board[i][j]) {
          airway[i][j] = true;
          for (int k = j + X - 1; k > j; k--) {
            if (k >= N || board[i][k] != board[i][j] || airway[i][k]) {
              flag = false;
              break;
            }
            airway[i][k] = true;
          }
        }

        prev = board[i][j];
      }

      if (flag) {
        cnt++;
      }
    }
  }
}
