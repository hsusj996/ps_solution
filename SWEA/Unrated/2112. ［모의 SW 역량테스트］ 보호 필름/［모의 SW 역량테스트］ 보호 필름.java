import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
  static StringBuilder result = new StringBuilder();
  static StringTokenizer st = null;
  static int T;
  static int D, W, K;
  static int[][] board;
  static int[] initialSet;
  static int[][] copyBoard;
  static int min;
  static boolean find;
  static int[] SelectedRow;
  static int[] SelectedColor;

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
      find = false;

      for (int i = 0; i < D; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < W; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      for (int i = 0; i <= D; i++) {
        SelectedRow = new int[i];
        SelectedColor = new int[i];
        Combi(0, 0, i);
        if (min != Integer.MAX_VALUE) {
          break;
        }
      }

      result.append(min).append("\n");
    }

    System.out.println(result);
  }

  private static void Combi(int prev, int depth, int targetDepth) {
    if (depth == targetDepth) {
      int usedCnt = SelectedRow.length;

      ColorPermutation(0, usedCnt);

      return;
    }

    for (int i = prev; i < D; i++) {
      SelectedRow[depth] = i;
      Combi(i + 1, depth + 1, targetDepth);
    }
  }

  private static void ColorPermutation(int depth, int targetDepth) {
    if (find) {
      return;
    }

    if (depth == targetDepth) {
      copyBoard = new int[D][W];

      for (int i = 0; i < D; i++) {
        copyBoard[i] = board[i].clone();
      }

      for (int i = 0; i < targetDepth; i++) {
        Arrays.fill(copyBoard[SelectedRow[i]], SelectedColor[i]);
      }

      if (CheckPass()) {
        min = targetDepth;
      }
      return;
    }

    SelectedColor[depth] = 0;
    ColorPermutation(depth + 1, targetDepth);

    SelectedColor[depth] = 1;
    ColorPermutation(depth + 1, targetDepth);
  }

  private static void SubSet(int depth) {
    if (find) {
      return;
    }

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
        find = true;
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
      int cnt = 0;
      int prev = copyBoard[0][i];
      boolean pass = false;
      for (int j = 0; j < D; j++) {
        if (prev == copyBoard[j][i]) {
          cnt++;
        } else {
          prev = copyBoard[j][i];
          cnt = 1;
        }

        if (cnt >= K) {
          pass = true;
          break;
        }
      }

      if (!pass) {
        return false;
      }
    }
    return true;
  }
}