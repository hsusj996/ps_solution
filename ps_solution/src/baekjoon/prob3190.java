package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob3190 {
  static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

  static class xy {
    int x;
    int y;

    public xy(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static class DirectionInfo {
    int time;
    char direction;

    public DirectionInfo(int time, char direction) {
      this.time = time;
      this.direction = direction;
    }
  }

  static boolean[][] board;
  static Queue<xy> snakeQ;
  static HashSet<xy> appleSet;
  static Queue<DirectionInfo> infoQ;
  static int N;
  static int K;
  static int L;
  static StringTokenizer st = null;
  static int snakeDirection;
  static xy snakeCurPos;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    board = new boolean[N + 1][N + 1];

    K = Integer.parseInt(br.readLine());
    appleSet = new HashSet<>();
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      appleSet.add(new xy(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    }

    L = Integer.parseInt(br.readLine());
    infoQ = new ArrayDeque<>();
    for (int i = 0; i < L; i++) {
      st = new StringTokenizer(br.readLine());
      infoQ.add(new DirectionInfo(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
    }

    snakeQ = new ArrayDeque<>();
    snakeCurPos = new xy(1, 1);
    snakeQ.add(new xy(1, 1));
    snakeDirection = 1;
    board[1][1] = true;

    int time = 0;
    while (true) {
      time++;

      // 방향 변경 확인
      if (infoQ.peek().time == time) {
        DirectionInfo d = infoQ.poll();
        if (d.direction == 'L') {
          snakeDirection += 3;
          snakeDirection %= 4;
        } else {
          snakeDirection++;
          snakeDirection %= 4;
        }
      }

      // 전진
      int nextX = snakeCurPos.x + d[0][snakeDirection];
      int nextY = snakeCurPos.y + d[1][snakeDirection];
    }

    System.out.println(time);
  }

  private static boolean GameOver() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'GameOver'");
  }
}
