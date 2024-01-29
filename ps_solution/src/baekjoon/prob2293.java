package baekjoon;

import java.util.*;
import java.io.*;

public class prob2293 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[] dp = new int[K + 1];
    dp[0] = 1;
    List<Integer> coin = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      coin.add(Integer.parseInt(br.readLine()));
    }

    for (int i = 0; i < coin.size(); i++) {
      for (int j = coin.get(i); j <= K; j++) {
        dp[j] += dp[j - coin.get(i)];
      }
    }

    System.out.println(dp[K]);
  }
}
