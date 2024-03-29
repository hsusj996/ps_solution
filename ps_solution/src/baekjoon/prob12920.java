package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class prob12920 {
  static class item {
    int weight;
    int value;

    public item(int weight, int value) {
      this.weight = weight;
      this.value = value;
    }

  }

  static StringTokenizer st = null;
  static int N, M;
  static List<item> itemList = new ArrayList<>();
  static int[][] DP;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int weight = Integer.parseInt(st.nextToken());
      int value = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());

      for (int m = 0; k > 0; m++) {
        int unit = (int) Math.pow(2, m);
        if (k > unit) {
          itemList.add(new item(weight * unit, value * unit));
          k -= unit;
        } else {
          itemList.add(new item(weight * k, value * k));
          break;
        }
      }
    }

    DP = new int[itemList.size() + 1][M + 1];

    for (int i = 1; i <= itemList.size(); i++) {
      item c = itemList.get(i - 1);
      for (int j = 0; j <= M; j++) {
        if (c.weight > j) {
          DP[i][j] = DP[i - 1][j];
        } else {
          DP[i][j] = Math.max(DP[i - 1][j], DP[i - 1][j - c.weight] + c.value);
        }
      }
    }

    System.out.println(DP[itemList.size()][M]);
    return;
  }
}
