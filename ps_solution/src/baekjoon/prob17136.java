package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class prob17136 {

  static class xy {
    int x;
    int y;

    public xy(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static StringTokenizer st = null;
  static int[][] board = new int[10][10];
  static int[] used = { 0, 5, 5, 5, 5, 5 };
  static List<xy> cdntList = new ArrayList<>();
  static int usedCnt = 30;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    for (int i = 0; i < 10; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 10; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
        if (board[i][j] == 1) {
          cdntList.add(new xy(i, j));
        }
      }
    }

    backTracking(0);

    if (usedCnt == 30) {
      usedCnt = -1;
    }
    System.out.println(usedCnt);
  }

  private static void backTracking(int n) {
    for (; n < cdntList.size(); n++) {
      xy c = cdntList.get(n);
      if (board[c.x][c.y] == 1) {
        break;
      }
    }

    if (n == cdntList.size()) {
      int usedSum = 0;
      for (int i = 1; i <= 5; i++) {
        usedSum += (5 - used[i]);
      }
      usedCnt = Math.min(usedCnt, usedSum);
      return;
    }

    xy cur = cdntList.get(n);
    for (int i = 1; i <= 5; i++) {
      if (used[i] == 0 || !checkPossible(i, cur.x, cur.y)) {
        continue;
      }

      // 붙히기
      setBoard(i, cur.x, cur.y, 0);
      used[i]--;
      backTracking(n + 1);
      setBoard(i, cur.x, cur.y, 1);
      used[i]++;
    }
  }

  private static void setBoard(int range, int x, int y, int n) {
    for (int i = x; i < x + range; i++) {
      for (int j = y; j < y + range; j++) {
        board[i][j] = n;
      }
    }
  }

  private static boolean checkPossible(int range, int x, int y) {
    for (int i = x; i < x + range; i++) {
      for (int j = y; j < y + range; j++) {
        if (IsOutBound(i, j) || board[i][j] == 0) {
          return false;
        }
      }
    }
    return true;
  }

  private static boolean IsOutBound(int x, int y) {
    return x < 0 || x >= 10 || y < 0 || y >= 10;
  }
}
