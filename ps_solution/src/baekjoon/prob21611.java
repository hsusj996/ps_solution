package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class prob21611 {
  static int N, M;
  static int[][] map;
  static int explodedMarble1Cnt = 0;
  static int explodedMarble2Cnt = 0;
  static int explodedMarble3Cnt = 0;
  static int[] d_row = { 0, 1, 0, -1 };
  static int[] d_col = { -1, 0, 1, 0 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int d = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());

      Blizzard(d, s);
      List<Integer> marbleList = getMarbleList();
      ArrangeMarbleList(marbleList);

      while (ExplodeMarble(marbleList)) {
        ArrangeMarbleList(marbleList);
      }

      List<Integer> newMarbleList = GroupingMarbleList(marbleList);
      SetMarbleMap(newMarbleList);
    }
  }

  private static void SetMarbleMap(List<Integer> newMarbleList) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'SetMarbleMap'");
  }

  private static List<Integer> GroupingMarbleList(List<Integer> marbleList) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'GroupingMarbleList'");
  }

  private static boolean ExplodeMarble(List<Integer> marbleList) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'ExplodeMarble'");
  }

  private static void ArrangeMarbleList(List<Integer> marbleList) {

  }

  private static List<Integer> getMarbleList() {
    List<Integer> ret = new ArrayList<>();
    int row = N / 2;
    int col = N / 2;
    int d = 3;
    for (int i = 1; i <= N; i++) {
      for (int j = i; j > 0; j--) {
        row += d_row[d];
        col += d_col[d];
        ret.add(map[row][col]);
      }

      d++;
      d %= 4;

      for (int j = i; j > 0; j--) {
        row += d_row[d];
        col += d_col[d];
        ret.add(map[row][col]);
      }

      d++;
      d %= 4;
    }

    for (int j = N; j > 0; j--) {
      row += d_row[d];
      col += d_col[d];
      ret.add(map[row][col]);
    }

    return ret;
  }

  private static void Blizzard(int d, int s) {
    int SharkPosRow = N / 2;
    int SharkPosCol = N / 2;
    for (int i = 1; i <= s; i++) {
      int newRow = SharkPosRow + d_row[d] * s;
      int newCol = SharkPosCol + d_col[d] * s;

      map[newRow][newCol] = 0;
    }
  }
}
