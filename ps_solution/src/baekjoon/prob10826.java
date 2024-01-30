package baekjoon;

import java.io.*;
import java.math.BigInteger;

public class prob10826 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    BigInteger[] dp = new BigInteger[N + 1];
    dp[0] = new BigInteger("0");

    if (N >= 1) {
      dp[1] = new BigInteger("1");
    }

    for (int i = 2; i <= N; i++) {
      dp[i] = dp[i - 1].add(dp[i - 2]);
    }

    System.out.println(dp[N]);
  }
}
