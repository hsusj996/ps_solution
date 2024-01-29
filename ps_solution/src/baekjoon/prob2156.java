package baekjoon;

import java.util.*;

public class prob2156 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }

        int[] dp = new int[N + 1];
        dp[1] = arr[1];

        if (N > 1) {
            dp[2] = arr[1] + arr[2];
        }

        for (int i = 3; i <= N; i++) {
            int caseA = dp[i - 2] + arr[i];
            int caseB = dp[i - 3] + arr[i - 1] + arr[i];
            int caseC = dp[i - 1];

            dp[i] = Math.max(caseC, Math.max(caseA, caseB));
        }

        System.out.println(dp[N]);
    }
}
