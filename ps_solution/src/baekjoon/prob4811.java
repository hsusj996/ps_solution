package baekjoon;

import java.util.*;
import java.io.*;
import java.math.*;

public class prob4811 {
  static BigInteger[] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    dp = new BigInteger[31];
    for (int i = 0; i <= 30; i++) {
      dp[i] = new BigInteger("0");
    }
    dp[0] = BigInteger.valueOf(1);
    dp[1] = BigInteger.valueOf(1);

    for (int i = 2; i <= 30; i++) {
      for (int j = 0; j < i; j++) {
        dp[i] = dp[i].add(dp[j].multiply(dp[i - j - 1]));
      }
    }

    while (true) {
      int input = Integer.parseInt(br.readLine());

      if (input == 0) {
        break;
      }

      System.out.println(dp[input]);
    }

    return;
  }
}
