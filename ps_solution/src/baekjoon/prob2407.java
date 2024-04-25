package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class prob2407 {
  static BigInteger[][] dp = new BigInteger[101][101];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    for (int i = 1; i <= n; i++) {
      dp[i][0] = new BigInteger("1");
      dp[i][i] = new BigInteger("1");
    }

    for (int i = 2; i <= n; i++) {
      for (int j = 1; j < i; j++) {
        dp[i][j] = dp[i - 1][j - 1].add(dp[i - 1][j]);
      }
    }

    System.out.println(dp[n][m]);
  }
}
