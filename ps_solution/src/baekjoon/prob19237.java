package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class prob19237 {
  static int N, M, k;
  static Space[][] map;

  static class Space {
    int sharkNum;
    int scent;
    int scentSharkNum;

    public Space(int sharkNum, int scent, int scentSharkNum) {
      this.sharkNum = sharkNum;
      this.scent = scent;
      this.scentSharkNum = scentSharkNum;
    }
  }

  static class Shark {
    int row;
    int col;
    int direction;

    public Shark(int row, int col, int direction) {
      this.row = row;
      this.col = col;
      this.direction = direction;
    }
  }

  static int[] d_row = { -1, 1, 0, 0 };
  static int[] d_col = { 0, 0, -1, 1 };
  static int[][][] sharkDirectionInfo;
  static List<Shark> sharkList = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    map = new Space[N + 1][N + 1];
    sharkDirectionInfo = new int[M + 1][5][4];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        map[i][j] = new Space(Integer.parseInt(st.nextToken()), 0, 0);

      }
    }

    for (int i = 1; i <= M; i++) {
      for (int j = 1; j <= 4; j++) {
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < 4; k++) {
          sharkDirectionInfo[i][j][k] = Integer.parseInt(st.nextToken());
        }
      }
    }

    int cnt = 0;
    while (sharkList.size() > 1) {
      cnt++;

    }

    System.out.println(cnt);
  }
}
