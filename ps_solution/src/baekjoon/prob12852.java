package baekjoon;

import java.util.Scanner;

public class prob12852 {
  static int N;
  static int[] dp = new int[1000001];
  static int[] dpIdx = new int[1000001];

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();

    dp[1] = 0;
    dp[N] = dpFunc(N);
    System.out.println(dp[N]);
    for (int i = N; i != 0; i = dpIdx[i]) {
      System.out.print(i + " ");
    }
  }

  private static int dpFunc(int n) {
    if (dp[n] != 0 || n == 1) {
      return dp[n];
    }

    int min = dpFunc(n - 1) + 1;
    dpIdx[n] = n - 1;

    if (n % 2 == 0) {
      if (min > dpFunc(n / 2) + 1) {
        min = dp[n / 2] + 1;
        dpIdx[n] = n / 2;
      }
    }
    if (n % 3 == 0) {
      if (min > dpFunc(n / 3) + 1) {
        min = dp[n / 3] + 1;
        dpIdx[n] = n / 3;
      }
    }

    return dp[n] = min;
  }
}
