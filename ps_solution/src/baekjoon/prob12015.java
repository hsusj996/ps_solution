package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prob12015 {
  private static final int INF = Integer.MAX_VALUE;
  static int N;
  static int[] dp;
  static int max = 0;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    dp = new int[N + 1];
    Arrays.fill(dp, INF);

    for (int i = 0; i < N; i++) {
      int input = Integer.parseInt(st.nextToken());
      int idx = binary_search(1, max, input);

      dp[idx + 1] = Math.min(dp[idx + 1], input);
      max = Math.max(max, idx + 1);
    }

    System.out.println(max);

  }

  private static int binary_search(int left, int right, int target) {
    int start = left;
    int end = right;
    while (start <= end) {
      int mid = (start + end) / 2;

      if (dp[mid] == target) {
        return mid - 1;
      }

      if (dp[mid] < target) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }

    if (start > right) {
      return right;
    } else {
      return end;
    }
  }
}
