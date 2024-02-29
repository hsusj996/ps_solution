import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
  static StringBuilder result = new StringBuilder();
  static StringTokenizer st = null;
  static int T, N, M, C;
  static int[][] board;
  static int[][] workers = new int[2][2];
  static boolean[][] isSelected;
  static int[] max = new int[2];
  static int maxOfSum;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    T = Integer.parseInt(br.readLine());
    for (int test_case = 1; test_case <= T; test_case++) {
      result.append("#").append(test_case).append(" ");

      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());

      board = new int[N][N];
      maxOfSum = 0;

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      // 벌통 경우의 수 선택
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N - M + 1; j++) {
          isSelected = new boolean[2][M];

          // 최댓값 초기화
          max[0] = 0;
          max[1] = 0;

          // 1번 일꾼 위치 결정
          workers[0][0] = i;
          workers[0][1] = j;
          SubSet1(0);

          // 2번 일꾼 위치 결정 (같은 행 먼저 검색)
          for (int k = j + M; k < N - M + 1; k++) {
            workers[1][0] = i;
            workers[1][1] = k;

            // 벌통 subset
            SubSet2(0);
          }

          // 2번 일꾼 위치 결정 (다음 행부터)
          for (int m = i + 1; m < N; m++) {
            for (int n = 0; n < N - M + 1; n++) {
              workers[1][0] = m;
              workers[1][1] = n;

              // 벌통 subset
              SubSet2(0);
            }
          }

          maxOfSum = Math.max(maxOfSum, max[0] + max[1]);
        }
      }

      // 출력
      result.append(maxOfSum).append("\n");
    }
    System.out.println(result);
  }

  private static void SubSet2(int depth) {
    if (depth == M) {
      int sumOfPriofit = 0;
      int sum = 0;
      for (int i = 0; i < M; i++) {
        if (isSelected[1][i]) {
          int v1 = board[workers[1][0]][workers[1][1] + i];
          sum += v1;
          sumOfPriofit += v1 * v1;
        }

        if (sum > C) {
          return;
        }
      }

      max[1] = Math.max(max[1], sumOfPriofit);

      return;
    }

    isSelected[1][depth] = true;
    SubSet2(depth + 1);
    isSelected[1][depth] = false;
    SubSet2(depth + 1);
  }

  private static void SubSet1(int depth) {
    if (depth == M) {
      int sumOfProfit = 0;
      int sum = 0;
      for (int i = 0; i < M; i++) {
        if (isSelected[0][i]) {
          int v1 = board[workers[0][0]][workers[0][1] + i];
          sum += v1;
          sumOfProfit += v1 * v1;
        }

        if (sum > C) {
          return;
        }
      }

      max[0] = Math.max(max[0], sumOfProfit);

      return;
    }

    isSelected[0][depth] = true;
    SubSet1(depth + 1);
    isSelected[0][depth] = false;
    SubSet1(depth + 1);
  }
}