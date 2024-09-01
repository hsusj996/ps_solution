package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class prob12738 {
  private static final int INF = Integer.MAX_VALUE;
  static int N;
  static int[] dp;
  static int[] cnt;
  static int[] arr;
  static int max = 0;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    dp = new int[N + 1];
    cnt = new int[N + 1];
    arr = new int[N + 1];
    Arrays.fill(dp, INF);

    for (int i = 1; i <= N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
      int idx = binary_search(1, max, arr[i]);

      dp[idx + 1] = Math.min(dp[idx + 1], arr[i]);
      cnt[i] = idx;
      max = Math.max(max, idx + 1);
    }

    System.out.println(max);

    Stack<Integer> stk = new Stack<>();
    for (int i = N; i > 0; i--) {
      if (cnt[i] == max - 1) {
        max--;
        stk.add(arr[i]);
      }
    }

    while(!stk.isEmpty()){
      System.out.print(stk.pop() + " ");
    }
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