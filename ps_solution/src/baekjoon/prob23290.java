package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class prob23290 {
  static int M;
  static int S;

  static class cdnt {
    int row;
    int col;

    cdnt(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

  // static class Fish extends cdnt {
  // int direction;

  // Fish(int row, int col, int direction) {
  // super(row, col);
  // this.direction = direction;
  // }
  // }

  static cdnt sharkPos;
  static List<Integer>[][] FishMap = new ArrayList[4][4];
  static int[][] map = new int[4][4];
  static int ans = 0;
  static int[] d_row = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
  static int[] d_col = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    M = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());

    // 입력
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());

      FishMap[row][col].add(direction);
    }
    st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    sharkPos = new cdnt(row, col);
    map[row][col] = -1;

    while (S-- > 0) {
      Simulation();
    }

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        ans += FishMap[i][j].size();
      }
    }

    System.out.println(ans);
  }

  private static void Simulation() {
    MoveFishes();
    MoveShark();
    UpdateMap();
    DuplicateFishes();
  }

  private static void DuplicateFishes() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'DuplicateFishes'");
  }

  private static void UpdateMap() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'UpdateMap'");
  }

  private static void MoveShark() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'MoveShark'");
  }

  private static void MoveFishes() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        for (int fish : FishMap[i][j]) {
          for (int k = 0; k < 8; k++) {
            int direction = (fish + k) % 8 == 0 ? 1 : (fish + k) % 8;
            if (isPossible(i, j, direction)) {
              FishMap[i + d_row[direction]][j + d_col[direction]].add(direction);
              FishMap[i][j].remove(fish);
            }
          }
        }
      }
    }
  }

  private static boolean isPossible(int row, int col, int direction) {
    int new_row = row + d_row[direction];
    int new_col
  }

}
