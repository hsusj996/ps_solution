package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea2112 {
  static StringBuilder result = new StringBuilder();
  static StringTokenizer st = null;
  static int T;
  static int D, W, K;
  static int[][] board;
  static int[] initialSet;
  static int[][] copyBoard;
  static int min;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    T = Integer.parseInt(br.readLine());
    for (int test_case = 1; test_case <= T; test_case++) {
      result.append("#").append(test_case).append(" ");
      st = new StringTokenizer(br.readLine());
      D = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());

      board = new int[D][W];
      initialSet = new int[D];
      min = Integer.MAX_VALUE;

      for (int i = 0; i < D; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < W; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      SubSet(0);

      result.append(min).append("\n");
    }

    System.out.println(result);
  }

  private static void SubSet(int depth) {
    if (depth == D) {
      copyBoard = new int[D][W];
      int usedCnt = 0;
      for (int i = 0; i < D; i++) {
        if (initialSet[i] == -1) {
          copyBoard[i] = board[i].clone();
          continue;
        }

        usedCnt++;
        Arrays.fill(copyBoard[i], initialSet[i]);
      }

      if (CheckPass()) {
        min = Math.min(min, usedCnt);
      }

      return;
    }

    initialSet[depth] = -1;
    SubSet(depth + 1);
    initialSet[depth] = 0;
    SubSet(depth + 1);
    initialSet[depth] = 1;
    SubSet(depth + 1);
  }

  private static boolean CheckPass() {
    for (int i = 0; i < W; i++) {
      int cnt = 1;
      int prev = copyBoard[0][i];
      boolean pass = false;
      for (int j = 1; j < D; j++) {
        if (prev == copyBoard[j][i]) {
          if (++cnt >= K) {
            pass = true;
            break;
          }
        } else {
          prev = copyBoard[0][i];
          cnt = 1;
        }
      }

      if (!pass) {
        return false;
      }
    }
    return true;
  }
}
