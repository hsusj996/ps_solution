package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
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

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + x;
      result = prime * result + y;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof xy)) {
        return false;
      }
      xy o = (xy) obj;
      return this.x == o.x && this.y == o.y ? true : false;
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

      // 전진
      snakeCurPos.x += d[0][snakeDirection];
      snakeCurPos.y += d[1][snakeDirection];

      if (GameOver()) {
        break;
      }

      // 갈 수 있는 곳이기 때문에 갱신
      if (appleSet.contains(snakeCurPos)) {
        snakeQ.add(new xy(snakeCurPos.x, snakeCurPos.y));
        board[snakeCurPos.x][snakeCurPos.y] = true;
        appleSet.remove(snakeCurPos);
      } else {
        snakeQ.add(new xy(snakeCurPos.x, snakeCurPos.y));
        board[snakeCurPos.x][snakeCurPos.y] = true;
        xy tail = snakeQ.poll();
        board[tail.x][tail.y] = false;
      }

      // 방향 변경 확인
      if (!infoQ.isEmpty() && infoQ.peek().time == time) {
        DirectionInfo d = infoQ.poll();
        if (d.direction == 'L') {
          snakeDirection += 3;
          snakeDirection %= 4;
        } else {
          snakeDirection++;
          snakeDirection %= 4;
        }
      }
    }

    System.out.println(time);
  }

  private static boolean GameOver() {
    return IsOutBound(snakeCurPos) || board[snakeCurPos.x][snakeCurPos.y] == true;
  }

  private static boolean IsOutBound(xy pos) {
    return pos.x <= 0 || pos.x > N || pos.y <= 0 || pos.y > N;
  }
}
