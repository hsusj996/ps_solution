import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
  static class xy {
    int x;
    int y;

    public xy(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static StringBuilder result = new StringBuilder();
  static StringTokenizer st = null;
  static int T, N, M;
  static int[][] board;
  static List<xy> homeList;
  static int maxCnt;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      result.append("#").append(test_case).append(" ");

      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      homeList = new ArrayList<>();
      maxCnt = 0;
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          if (Integer.parseInt(st.nextToken()) == 1) {
            homeList.add(new xy(i, j));
          }
        }
      }

      int maxCost = homeList.size() * M;

      for (int range = 1; range * range + (range - 1) * (range - 1) <= maxCost; range++) {
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
            int cnt = 0;
            for (xy c : homeList) {
              if (Math.abs(c.x - i) + Math.abs(c.y - j) <= range - 1) {
                cnt++;
              }
            }

            if (range * range + (range - 1) * (range - 1) <= M * cnt) {
              maxCnt = Math.max(maxCnt, cnt);
            }
          }
        }
      }

      result.append(maxCnt).append("\n");
    }

    System.out.println(result);
  }
}