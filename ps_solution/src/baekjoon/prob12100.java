package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob12100 {
  static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };
  static int ans;
  static int[][] board;
  static int N;
  static StringTokenizer st = null;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    board = new int[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    dfs(0, board);

    System.out.println(ans);
  }

  private static void dfs(int depth, int[][] curBoard) {
    if (depth == 5) {
      ans = Math.max(ans, maxOfBoard(curBoard));
      return;
    }

    for (int i = 0; i < 4; i++) {
      int[][] newBoard = moveBoard(curBoard, i);

      // PrintForDebug(curBoard, newBoard, i, depth);

      dfs(depth + 1, newBoard);
    }
  }

  private static void PrintForDebug(int[][] curBoard, int[][] newBoard, int direction, int depth) {
    switch (direction) {
      case 0:
        System.out.println("Up");
        break;
      case 1:
        System.out.println("Right");
        break;
      case 2:
        System.out.println("Down");
        break;
      case 3:
        System.out.println("Left");
        break;

      default:
        break;
    }

    System.out.println(depth);
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        System.out.print(curBoard[i][j] + " ");
      }
      System.out.print("       ");
      for (int j = 0; j < N; j++) {
        System.out.print(newBoard[i][j] + " ");
      }
      System.out.println();
    }

    System.out.println("--------------------------------");
    System.out.println();
  }

  private static int[][] moveBoard(int[][] curBoard, int direction) {
    int[][] ret = new int[N][N];
    for (int i = 0; i < N; i++) {
      ret[i] = curBoard[i].clone();
    }

    switch (direction) {
      case 0:
        moveUp(ret);
        break;
      case 1:
        moveRight(ret);
        break;
      case 2:
        moveDown(ret);
        break;
      case 3:
        moveLeft(ret);
        break;
      default:
        break;
    }

    return ret;
  }

  private static void moveLeft(int[][] curBoard) {
    for (int i = 0; i < N; i++) {
      // 결과를 저장할 Queue
      Queue<Integer> resultQ = new ArrayDeque<>();
      int tmp = 0;
      for (int j = 0; j < N; j++) {
        if(curBoard[i][j] == 0){
          continue;
        }
        if (tmp == 0) {
          tmp = curBoard[i][j];
          continue;
        }
        if (tmp == curBoard[i][j]) {
          resultQ.add(2 * tmp);
          tmp = 0;
        } else {
          resultQ.add(tmp);
          tmp = curBoard[i][j];
        }
      }
      if (tmp != 0) {
        resultQ.add(tmp);
      }
      // 정리
      for (int j = 0; j < N; j++) {
        if (resultQ.isEmpty()) {
          for (int k = j; k < N; k++) {
            curBoard[i][k] = 0;
          }
          break;
        }
        curBoard[i][j] = resultQ.poll();
      }
    }
  }

  private static void moveDown(int[][] curBoard) {
    for (int j = 0; j < N; j++) {
      // 결과를 저장할 Queue
      Queue<Integer> resultQ = new ArrayDeque<>();
      int tmp = 0;
      for (int i = N - 1; i >= 0; i--) {
        if(curBoard[i][j] == 0){
          continue;
        }
        if (tmp == 0) {
          tmp = curBoard[i][j];
          continue;
        }
        if (tmp == curBoard[i][j]) {
          resultQ.add(2 * tmp);
          tmp = 0;
        } else {
          resultQ.add(tmp);
          tmp = curBoard[i][j];
        }
      }
      if (tmp != 0) {
        resultQ.add(tmp);
      }
      // 정리
      for (int i = N - 1; i >= 0; i--) {
        if (resultQ.isEmpty()) {
          for (int k = i; k >= 0; k--) {
            curBoard[k][j] = 0;
          }
          break;
        }
        curBoard[i][j] = resultQ.poll();
      }
    }
  }

  private static void moveRight(int[][] curBoard) {
    for (int i = 0; i < N; i++) {
      // 결과를 저장할 Queue
      Queue<Integer> resultQ = new ArrayDeque<>();
      int tmp = 0;
      for (int j = N - 1; j >= 0; j--) {
        if(curBoard[i][j] == 0){
          continue;
        }
        if (tmp == 0) {
          tmp = curBoard[i][j];
          continue;
        }
        if (tmp == curBoard[i][j]) {
          resultQ.add(2 * tmp);
          tmp = 0;
        } else {
          resultQ.add(tmp);
          tmp = curBoard[i][j];
        }
      }
      if (tmp != 0) {
        resultQ.add(tmp);
      }
      // 정리
      for (int j = N - 1; j >= 0; j--) {
        if (resultQ.isEmpty()) {
          for (int k = j; k >= 0; k--) {
            curBoard[i][k] = 0;
          }
          break;
        }
        curBoard[i][j] = resultQ.poll();
      }
    }
  }

  private static void moveUp(int[][] curBoard) {
    for (int j = 0; j < N; j++) {
      // 결과를 저장할 Queue
      Queue<Integer> resultQ = new ArrayDeque<>();
      int tmp = 0;
      for (int i = 0; i < N; i++) {
        if(curBoard[i][j] == 0){
          continue;
        }
        if (tmp == 0) {
          tmp = curBoard[i][j];
          continue;
        }
        if (tmp == curBoard[i][j]) {
          resultQ.add(2 * tmp);
          tmp = 0;
        } else {
          resultQ.add(tmp);
          tmp = curBoard[i][j];
        }
      }
      if (tmp != 0) {
        resultQ.add(tmp);
      }
      // 정리
      for (int i = 0; i < N; i++) {
        if (resultQ.isEmpty()) {
          for (int k = i; k < N; k++) {
            curBoard[k][j] = 0;
          }
          break;
        }
        curBoard[i][j] = resultQ.poll();
      }
    }
  }

  private static int maxOfBoard(int[][] curBoard) {
    int max = -1;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        max = Math.max(max, curBoard[i][j]);
      }
    }

    return max;
  }
}
