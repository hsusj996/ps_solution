package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea4014 {
  static StringBuilder result = new StringBuilder();
  static StringTokenizer st = null;
  static int T, N, X;
  static int[][] board;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    T = Integer.parseInt(br.readLine());
    for (int test_case = 1; test_case <= T; test_case++) {
      result.append("#").append(test_case).append(" ");
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      X = Integer.parseInt(st.nextToken());

      board = new int[N][N];
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      // 행 검사
      for (int i = 0; i < N; i++) {
        boolean flag = true;
        int prev = board[i][0];
        for (int j = 1; j < N; j++) {
          if (prev == board[i][j])
            continue;

          if (Math.abs(prev - board[i][j]) >= 2) {
            flag = false;
            break;
          }

          if (prev < board[i][j]) {

          }

        }
      }
    }
  }
}
