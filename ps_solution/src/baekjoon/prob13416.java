package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob13416 {
  static int T;
  static int N;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());
    for (int test_case = 1; test_case <= T; test_case++) {
      N = Integer.parseInt(br.readLine());

      int sum = 0;
      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int bestProfit = 0;
        for (int j = 0; j < 3; j++) {
          bestProfit = Math.max(bestProfit, Integer.parseInt(st.nextToken()));
        }

        sum += bestProfit;
      }

      System.out.println(sum);
    }
  }
}
