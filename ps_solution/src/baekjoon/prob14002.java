package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class prob14002 {
  private static final int INF = 1_000_000_000;
  static int N;
  static int[] dp;
  static int[] arr;
  static int[] maxArr;
  static int max = 0;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    arr = new int[N + 1];
    maxArr = new int[N + 1];
    dp = new int[N + 1];
    Arrays.fill(dp, INF);

    for (int i = 1; i <= N; i++) {
      int input = Integer.parseInt(st.nextToken());
      arr[i] = input;
      int idx = binary_search(1, max, input);

      dp[idx + 1] = Math.min(dp[idx + 1], input);
      maxArr[idx + 1] = Math.max(maxArr[idx + 1], input);
      max = Math.max(max, idx + 1);
    }

    Stack<Integer> stk = new Stack<>();
    for (int cur = dp[max]; cur != 0 && cur != INF; cur = prev[cur]) {
      stk.add(cur);
    }

    sb.append(max).append("\n");
    while (!stk.isEmpty()) {
      sb.append(stk.pop()).append(" ");
    }

    System.out.println(sb.toString());
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
