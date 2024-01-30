package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob13172 {
  static int M;
  static long ans = 0;
  static long N = 1;
  static long S = 0;
  static long MOD = 1_000_000_007;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    M = Integer.parseInt(br.readLine());

    for (int i = 0; i < M; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());

      N = N * n;
      S = N * s + n * S;
      N %= MOD;
      S %= MOD;
    }

    // 기약분수
    if (N % S != 0) {

    } else {

    }
  }
}