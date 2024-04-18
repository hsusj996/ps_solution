import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] dp;
  static int[] arr;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    arr = new int[N + 1];
    dp = new int[N + 1];

    for (int i = 1; i <= N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    // DP 테이블 작성 O(n) = n*n
    dp[0] = 0;
    for (int i = 1; i <= N; i++) {
      dp[i] = 1;
      for (int j = i - 1; j > 0; j--) {
        if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
          dp[i] = dp[j] + 1;
        }
      }
    }

    // 최댓값 찾기
    int max = 0;
    int maxIdx = 0;
    for (int i = 1; i <= N; i++) {
      if (max < dp[i]) {
        max = dp[i];
        maxIdx = i;
      }
    }

    // 역순으로 탐색하면서 수열 저장
    Stack<Integer> stk = new Stack<>();
    stk.add(arr[maxIdx]);

    int prev = dp[maxIdx];
    int prevIdx = maxIdx;
    for (int i = maxIdx - 1; i > 0; i--) {
      if (dp[i] == prev - 1 && arr[i] < arr[prevIdx]) {
        stk.add(arr[i]);
        prev--;
        prevIdx = i;
      }
    }

    // 출력
    sb.append(dp[maxIdx]).append("\n");
    while (!stk.isEmpty()) {
      sb.append(stk.pop()).append(" ");
    }
    System.out.println(sb.toString());
  }
}