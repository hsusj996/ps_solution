package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob2166 {
  static int N;
  static long[][] cdnt;
  static StringTokenizer st = null;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    cdnt = new long[N][2];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      cdnt[i][0] = Integer.parseInt(st.nextToken());
      cdnt[i][1] = Integer.parseInt(st.nextToken());
    }

    double sum = 0;
    for (int i = 1; i < N - 1; i++) {
      sum += outerProduct(cdnt[0][0], cdnt[0][1], cdnt[i][0], cdnt[i][1], cdnt[i + 1][0], cdnt[i + 1][1]) / (double) 2;
    }

    String result = String.format("%.1f", Math.abs(sum));
    System.out.println(result);
  }

  private static long outerProduct(long x1, long y1, long x2, long y2, long x3, long y3) {
    return x1 * y2 + x2 * y3 + x3 * y1 - x2 * y1 - x3 * y2 - x1 * y3;
  }
}
